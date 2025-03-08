package com.example.mangaapp.Domain.RepoInterface

interface HistoryRepo {
    suspend fun addingHistoryManga(mangaId:String)

}