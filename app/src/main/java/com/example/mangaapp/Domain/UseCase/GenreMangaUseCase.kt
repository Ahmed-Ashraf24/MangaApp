package com.example.mangaapp.Domain.UseCase

import com.example.mangaapp.Data.RepoImp.MangaRepositoryImpl
import com.example.mangaapp.Domain.Entity.Manga

class GenreMangaUseCase {
    private val mangaGenre= MangaRepositoryImpl()

    suspend fun getMangaByGenre(genre :String): ArrayList<Manga> {
        return mangaGenre.getMangaByItsGenre(genre)

    }
}