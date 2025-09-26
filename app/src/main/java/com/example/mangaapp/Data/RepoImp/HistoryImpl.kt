package com.example.mangaapp.Data.RepoImp

import com.example.mangaapp.Data.DataSource.DataBase.DataBaseClient
import com.example.mangaapp.Data.DataSource.DataBase.Remote.RemoteDataBase
import com.example.mangaapp.Domain.RepoInterface.HistoryRepo

class HistoryImpl(private val dataBaseClient: DataBaseClient) :HistoryRepo{
    override suspend fun addingHistoryManga(mangaId: String) {
        dataBaseClient.addToHistory(mangaId)
    }

}