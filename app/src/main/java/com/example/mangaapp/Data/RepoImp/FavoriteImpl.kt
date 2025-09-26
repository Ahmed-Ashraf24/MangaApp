package com.example.mangaapp.Data.RepoImp

import com.example.mangaapp.Data.DataSource.DataBase.DataBaseClient
import com.example.mangaapp.Data.DataSource.DataBase.Remote.RemoteDataBase
import com.example.mangaapp.Domain.RepoInterface.FavoriteRepo

class FavoriteImpl(private val dataBaseClient: DataBaseClient):FavoriteRepo {
    override suspend fun addingFavoriteManga(mangaId: String) {
        dataBaseClient.addMangaToFav(mangaId)

    }


}