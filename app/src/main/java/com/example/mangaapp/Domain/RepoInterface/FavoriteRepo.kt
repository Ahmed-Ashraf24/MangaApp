package com.example.mangaapp.Domain.RepoInterface

interface FavoriteRepo {
    suspend fun addingFavoriteManga(mangaId:String)

}