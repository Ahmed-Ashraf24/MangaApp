package com.example.mangaapp.Data.DataSource.API

import com.example.mangaapp.Data.Models.APIModel.MangaDexApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    val api: MangaDexApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.mangadex.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MangaDexApi::class.java)
}

}