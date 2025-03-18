package com.example.mangaapp.Domain.UseCase.Manga

import com.example.mangaapp.Data.RepoImp.MangaRepositoryImpl
import com.example.mangaapp.Domain.Entity.Manga

class SearchMangaUseCase {
    private val searchManga=MangaRepositoryImpl()
    suspend fun getMangaByTitle(title :String): ArrayList<Manga> {
        return searchManga.getMangaByTitle(title)

    }
}