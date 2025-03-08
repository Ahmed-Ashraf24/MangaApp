package com.example.mangaapp.Data.RepoImp

import com.example.mangaapp.Data.DataSource.DataBase.DataBaseClient
import com.example.mangaapp.Data.DataSource.DataBase.Remote.RemoteDataBase
import com.example.mangaapp.Domain.RepoInterface.HistoryRepo

class RemoteHistoryImpl :HistoryRepo{
    val remoteDataBase : DataBaseClient = RemoteDataBase()
    override suspend fun addingHistoryManga(mangaId: String) {
        remoteDataBase.addToHistory(mangaId)
    }

}