package com.example.mangaapp.Data.RepoImp

import android.annotation.SuppressLint
import android.util.Log
import com.example.mangaapp.Data.DataSource.API.RetrofitClient.api
import com.example.mangaapp.Data.Mapper.ChapterMapper
import com.example.mangaapp.Data.Mapper.ChapterPageMapper
import com.example.mangaapp.Data.Mapper.MangaMapper
import com.example.mangaapp.Data.Models.APIModel.`ِApiResonse`.MangaResponse
import com.example.mangaapp.Domain.Entity.Chapter
import com.example.mangaapp.Domain.Entity.ChapterPanels
import com.example.mangaapp.Domain.Entity.Manga
import com.example.mangaapp.Domain.RepoInterface.MangaRepository
import retrofit2.Response

class MangaDexImpl : MangaRepository {
    @SuppressLint("SuspiciousIndentation")
    override suspend fun getLatestManga(): ArrayList<Manga> {

        val response = api.getAllManga(
            options = mapOf(
                "order[latestUploadedChapter]" to "desc"
            )
        )
        response
        return getMangaFromResponse(response)

    }

    override suspend fun getPopularManga(): ArrayList<Manga> {
        val response = api.getAllManga(
            options = mapOf(
                "order[followedCount]" to "desc"
            )
        )
        return getMangaFromResponse(response)
    }

    override suspend fun getRecommendedManga(): ArrayList<Manga> {
        val response = api.getAllManga(
            options = mapOf(
                "order[relevance]" to "desc"
            )
        )
        return getMangaFromResponse(response)
    }

    override suspend fun getMangaByTitle(mangaTitle: String): ArrayList<Manga> {
        val response = api.getMangaByTitle(mangaTitle)
        return getMangaFromResponse(response)

    }

    override suspend fun getMangaByItsGenre(mangaGenre: String): ArrayList<Manga> {
        val response = api.getMangaByGenre(genre = mangaGenre)

        return getMangaFromResponse(response)
    }

    override suspend fun getMangaChapters(mangaId: String): ArrayList<Chapter> {
        val chapterResponse = api.getAllChapters(mangaId)
        if (chapterResponse.isSuccessful) {
            Log.d("Chapter data from repo", chapterResponse.body().toString())
            Log.d("response code",chapterResponse.code().toString())
            Log.d("response message",chapterResponse.message())
            return ArrayList(chapterResponse.body()!!.data.mapNotNull { chapterData ->

                ChapterMapper.toChapter(chapterData)
            
            }
            )
        } else {
            Log.d("response code",chapterResponse.code().toString())
            Log.d("response message",chapterResponse.message())
            return ArrayList()
        }
    }

    override suspend fun getMoreMangaChapters(
        mangaId: String,
        offsetNumber: Int
    ): ArrayList<Chapter> {
        val chapterResponse = api.getAllChapters(mangaId = mangaId, offset = offsetNumber)

        if (chapterResponse.isSuccessful) {
            Log.d("response code",chapterResponse.code().toString())
            Log.d("response message",chapterResponse.message())
            return ArrayList(chapterResponse.body()!!.data.mapNotNull { chapterData ->
                ChapterMapper.toChapter(chapterData)
            })
        } else {
            Log.d("response code",chapterResponse.code().toString())
            Log.d("response message",chapterResponse.message())
            return ArrayList()
        }
    }

    override suspend fun getChapterPanels(chapter: Chapter): ChapterPanels? {
        val pagesResponse = api.getChapterImages(chapterId = chapter.chapterId)
        if (pagesResponse.isSuccessful) {
            val pagesURL = ArrayList<String>()
            Log.d("response code",pagesResponse.code().toString())
            Log.d("response message",pagesResponse.message())

            Log.d("Chapter panels from repo", pagesResponse.body().toString())

            pagesResponse.body()!!.chapter.data.forEach {

                val pageURL =
                    "${pagesResponse.body()!!.baseUrl}/data/${pagesResponse.body()!!.chapter.hash}/$it"
                pagesURL.add(pageURL)
            }
            return ChapterPageMapper.toChapterPanels(chapter, pagesURL)
        } else {
            Log.d("response code",pagesResponse.code().toString())
            Log.d("response message",pagesResponse.message())
            return null
        }
    }

    override suspend fun getMangaDetails(mangaId: String): Manga? {
        val response = api.getMangaDetails(mangaId)
        return if(response.isSuccessful){
            val mangaData = response.body()!!.data
            val imageURL=fetchCoverUrl(mangaId)

            MangaMapper.toManga(mangaData,imageURL!!)
        } else{
            null
        }
    }

    private suspend fun fetchCoverUrl(mangaId: String): String? {
        val response = api.getMangaDetails(mangaId)
        if (response.isSuccessful) {
            val manga = response.body()?.data
            val coverFileName =
                manga?.relationships?.find { it.type == "cover_art" }?.attributes?.fileName
            Log.d("cover url",coverFileName.toString())

            return coverFileName?.let { "https://uploads.mangadex.org/covers/$mangaId/$it" }
        }
        return null
    }

    private suspend fun getMangaFromResponse(response: Response<MangaResponse>): ArrayList<Manga> {
        return try {
            if (response.isSuccessful && response.body() != null) {
                Log.d("manga response", response.body()!!.data.first().toString())

                ArrayList(response.body()!!.data.mapNotNull { mangaData ->
                    val mangaId = mangaData.id
                    Log.d("manga genre id", mangaId)
                    Log.d("manga genre name :", mangaData.attributes.title.toString())


                    val coverUrl = fetchCoverUrl(mangaId)
                    MangaMapper.toManga(mangaData, coverUrl!!)
                })
            } else {
                ArrayList()
            }
        } catch (e: Exception) {
            ArrayList()
        }
    }

}


