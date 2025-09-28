package com.example.mangaapp.Domain.UseCase.Manga

import com.example.mangaapp.Domain.Entity.Manga
import com.example.mangaapp.Domain.RepoInterface.MangaRepository

class MangaUseCase(private val mangaRepo:MangaRepository) {
    suspend fun getPopularManga():ArrayList<Manga>{

        return mangaRepo.getPopularManga()

    }
    suspend fun getRecommendedManga():ArrayList<Manga>{
        return mangaRepo.getRecommendedManga()
    }
    suspend fun getMangaByTitle(title :String): ArrayList<Manga> {
        return mangaRepo.getMangaByTitle(title)

    }
    suspend fun getLatestManga():ArrayList<Manga>{

        return mangaRepo.getLatestManga()
    }
    suspend fun getMangaFromId(mangaId: String): Manga {
        return mangaRepo.getMangaDetails(mangaId)!!
    }
    suspend fun getMangaByGenre(genre :String): ArrayList<Manga> {
        return mangaRepo.getMangaByItsGenre(genre)

    }
}