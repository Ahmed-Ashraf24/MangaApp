package com.example.mangaapp.Domain.UseCase

import com.example.mangaapp.Data.RepoImp.MangaRepositoryImpl
import com.example.mangaapp.Domain.Entity.Manga
import com.example.mangaapp.Domain.RepoInterface.MangaRepository

class RecommendationUseCase {
   private val recommendedMangaRepo:MangaRepository=MangaRepositoryImpl()
    suspend fun getRecommendedManga():ArrayList<Manga>{
        return recommendedMangaRepo.getRecommendedManga()
    }
}