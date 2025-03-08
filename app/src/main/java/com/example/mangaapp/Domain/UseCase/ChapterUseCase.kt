package com.example.mangaapp.Domain.UseCase

import com.example.mangaapp.Data.RepoImp.MangaRepositoryImpl
import com.example.mangaapp.Domain.Entity.Chapter
import com.example.mangaapp.Domain.RepoInterface.MangaRepository

class ChapterUseCase {
    val mangaChapters:MangaRepository=MangaRepositoryImpl()
    suspend fun getAllChapters(mangaId:String):ArrayList<Chapter>{
        return mangaChapters.getMangaChapters(mangaId)
    }
}