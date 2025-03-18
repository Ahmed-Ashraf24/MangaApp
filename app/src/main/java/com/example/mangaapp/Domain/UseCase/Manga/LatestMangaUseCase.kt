package com.example.mangaapp.Domain.UseCase.Manga

import com.example.mangaapp.Data.RepoImp.MangaRepositoryImpl
import com.example.mangaapp.Domain.Entity.Manga
import com.example.mangaapp.Domain.RepoInterface.MangaRepository

class LatestMangaUseCase  {
    private val latestManga:MangaRepository=MangaRepositoryImpl()
    suspend fun getAllManga():ArrayList<Manga>{

        return latestManga.getLatestManga()
    }
}