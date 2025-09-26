package com.example.mangaapp.Domain.UseCase.Manga

import com.example.mangaapp.Data.RepoImp.MangaRepositoryImpl
import com.example.mangaapp.Data.RepoImp.FavoriteImpl
import com.example.mangaapp.Domain.Entity.Manga
import com.example.mangaapp.Domain.RepoInterface.FavoriteRepo
import com.example.mangaapp.Domain.RepoInterface.MangaRepository

class FavMangaUseCase(private val favoriteRepo: FavoriteRepo,private val mangaRepo:MangaRepository) {
    suspend fun addMangaToFavList(mangaId:String){
        favoriteRepo.addingFavoriteManga(mangaId = mangaId)
    }
    suspend fun getMangaFromId(mangaId: String): Manga {
        return mangaRepo.getMangaDetails(mangaId)!!
    }

}