package com.example.mangaapp.Domain.UseCase

import com.example.mangaapp.Data.RepoImp.MangaRepositoryImpl
import com.example.mangaapp.Data.RepoImp.RemoteFavoriteImpl
import com.example.mangaapp.Domain.Entity.Manga
import com.example.mangaapp.Domain.RepoInterface.FavoriteRepo
import com.example.mangaapp.Domain.RepoInterface.MangaRepository

class FavMangaUseCase {
    val remoteFavManga:FavoriteRepo=RemoteFavoriteImpl()
    val mangaRepo:MangaRepository=MangaRepositoryImpl()

    suspend fun addMangaToFavList(mangaId:String){
        remoteFavManga.addingFavoriteManga(mangaId = mangaId)
    }
    suspend fun getMangaFromId(mangaId: String): Manga {
        return mangaRepo.getMangaDetails(mangaId)!!
    }

}