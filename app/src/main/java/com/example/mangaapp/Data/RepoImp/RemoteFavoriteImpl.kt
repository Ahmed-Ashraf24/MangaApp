package com.example.mangaapp.Data.RepoImp

import com.example.mangaapp.Data.DataSource.DataBase.DataBaseClient
import com.example.mangaapp.Data.DataSource.DataBase.Remote.RemoteDataBase
import com.example.mangaapp.Domain.RepoInterface.FavoriteRepo
import com.google.firebase.auth.FirebaseAuth

class RemoteFavoriteImpl:FavoriteRepo {
    val remoteDataBase :DataBaseClient=RemoteDataBase()
    override suspend fun addingFavoriteManga(mangaId: String) {
            remoteDataBase.addMangaToFav(mangaId)

    }


}