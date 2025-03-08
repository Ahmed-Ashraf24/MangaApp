package com.example.mangaapp

import android.util.Log
import com.example.mangaapp.Data.DataSource.API.RetrofitClient
import com.example.mangaapp.Utilities.Constants

suspend fun main() {
    val genreUUID = "391b0423-d847-456f-aff0-8b0cfc03066b" // Action genre UUID
val gebnre=Constants.GENRES.getValue("Action")
    val response = RetrofitClient.api.getMangaByTitle("solo leveling")
    val mangaId=response.body()!!.data[1].id
    val chapterResponse=RetrofitClient.api.getAllChapters(mangaId, offset = 101)
    println("API_RESPONSE "+ "Response: ${chapterResponse.body()?.data!!.size} manga found.")
    println("API_RESPONSE "+ "Response: ${chapterResponse.body()} body.")
    val chapterNumbers = chapterResponse.body()!!.data.mapNotNull { it.attributes.chapter }

    println("Chapters: $chapterNumbers")
    chapterResponse.body()!!.data.forEach {chapter->
        val pageResponse = RetrofitClient.api.getChapterImages(chapter.id)

        if (pageResponse != null) {
            println("Base URL: ${pageResponse.body()!!.baseUrl}")
            println("Hash: ${pageResponse.body()!!.chapter.hash}")

            val imageUrls = pageResponse.body()!!.chapter.data.map { "${pageResponse.body()!!.baseUrl}/data/${pageResponse.body()!!.chapter.hash}/$it" }
            println("Image URLs: $imageUrls")
        } else {
            println("Failed to fetch images")
        }



    }

    response.body()!!.data.forEach {manga->
        println("MANGA_TITLE "+ "Title: ${manga.attributes.title["en"] ?: "No Title"}")
    }
    if (response.isSuccessful) {
       // println("Success: ${response.body()}")
    } else {
        println("API Error: ${response.code()} - ${response.errorBody()?.string()}")
    }
//    val mangaId=response.body()!!.data[0].id
//    val coverResponse=RetrofitClient.api.getMangaDetails(mangaId)
//    val coverFileName = coverResponse.body()!!.data?.relationships?.find { it.type == "cover_art" }?.attributes?.fileName
//
//    println("https://uploads.mangadex.org/covers/${mangaId}/${coverFileName}")
}