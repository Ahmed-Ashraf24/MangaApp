package com.example.mangaapp.Domain.UseCase

import com.example.mangaapp.Data.RepoImp.MangaRepositoryImpl
import com.example.mangaapp.Data.RepoImp.RemoteFavoriteImpl
import com.example.mangaapp.Data.RepoImp.RemoteHistoryImpl
import com.example.mangaapp.Domain.Entity.Manga
import com.example.mangaapp.Domain.RepoInterface.FavoriteRepo
import com.example.mangaapp.Domain.RepoInterface.HistoryRepo
import com.example.mangaapp.Domain.RepoInterface.MangaRepository

class HistoryMangaUseCase {
    val remoteFavManga: HistoryRepo = RemoteHistoryImpl()
    val mangaRepo: MangaRepository = MangaRepositoryImpl()

    suspend fun addMangaToHistoryList(mangaId:String){
        remoteFavManga.addingHistoryManga(mangaId = mangaId)
    }
    suspend fun getMangaFromId(mangaId: String): Manga {
        return mangaRepo.getMangaDetails(mangaId)!!
    }
}