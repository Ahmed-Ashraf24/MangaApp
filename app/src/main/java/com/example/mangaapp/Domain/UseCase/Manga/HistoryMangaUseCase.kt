package com.example.mangaapp.Domain.UseCase.Manga

import com.example.mangaapp.Data.RepoImp.MangaRepositoryImpl
import com.example.mangaapp.Data.RepoImp.HistoryImpl
import com.example.mangaapp.Domain.Entity.Manga
import com.example.mangaapp.Domain.RepoInterface.FavoriteRepo
import com.example.mangaapp.Domain.RepoInterface.HistoryRepo
import com.example.mangaapp.Domain.RepoInterface.MangaRepository

class HistoryMangaUseCase(private val historyRepo:HistoryRepo,private val mangaRepo: MangaRepository) {
    suspend fun addMangaToHistoryList(mangaId:String){
        historyRepo.addingHistoryManga(mangaId = mangaId)
    }
    suspend fun getMangaFromId(mangaId: String): Manga {
        return mangaRepo.getMangaDetails(mangaId)!!
    }
}