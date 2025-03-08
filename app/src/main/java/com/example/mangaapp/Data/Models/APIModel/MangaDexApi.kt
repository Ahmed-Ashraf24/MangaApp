package com.example.mangaapp.Data.Models.APIModel

import com.example.mangaapp.Data.Models.APIModel.Response.MangaDetailResponse
import com.example.mangaapp.Data.Models.APIModel.`ِApiResonse`.ChapterPagesData
import com.example.mangaapp.Data.Models.APIModel.`ِApiResonse`.ChapterPagesResponse
import com.example.mangaapp.Data.Models.APIModel.`ِApiResonse`.MangaResponse
import com.example.mangaapp.Data.Models.APIModel.`ِApiResonse`.ChapterResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap


interface MangaDexApi {
    @GET("manga")
    suspend fun getAllManga(
        @Query("limit") limit: Int = 10,
        @Query("offset") offset: Int = 0,
        @QueryMap options: Map<String, String>
    ): Response<MangaResponse>

    @GET("manga/{id}")
    suspend fun getMangaDetails(
        @Path("id") mangaId: String,
        @Query("includes[]") includes: List<String> = listOf("cover_art")
    ): Response<MangaDetailResponse>

    @GET("manga")
    suspend fun getMangaByTitle(
        @Query("title") title: String
    ): Response<MangaResponse>


    @GET("manga")
    suspend fun getMangaByGenre(
        @Query("includes[]") includes: String = listOf("cover_art").toString(),
        @Query("limit") limit: Int = 20,
        @Query("contentRating[]") contentRating: List<String> = listOf(
            "safe",
            "suggestive",
            "erotica"
        ),
        @Query("order[followedCount]") orderFollowedCount: String = "desc",
        @Query("order[relevance]") orderRelevance: String = "desc",
        @Query("includedTags[]") genre: String
    ): Response<MangaResponse>


    @GET("chapter")
    suspend fun getAllChapters(
        @Query("manga") mangaId: String,
        @Query("limit") limit: Int = 100,
        @Query("offset") offset: Int = 0,
        @Query("translatedLanguage[]") lang: String = "en",
        @Query("order[chapter]") order: String = "desc",
    ): Response<ChapterResponse>

    @GET("at-home/server/{chapterId}")
    suspend fun getChapterImages(@Path("chapterId") chapterId: String): Response<ChapterPagesResponse>

}

