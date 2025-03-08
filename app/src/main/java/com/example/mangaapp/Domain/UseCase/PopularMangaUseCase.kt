package com.example.mangaapp.Domain.UseCase

import com.example.mangaapp.Data.RepoImp.MangaRepositoryImpl
import com.example.mangaapp.Domain.Entity.Manga
import com.example.mangaapp.Domain.RepoInterface.MangaRepository

class PopularMangaUseCase {
    private val popularManga: MangaRepository = MangaRepositoryImpl()
    suspend fun getAllManga():ArrayList<Manga>{

        return popularManga.getPopularManga()
    }

}