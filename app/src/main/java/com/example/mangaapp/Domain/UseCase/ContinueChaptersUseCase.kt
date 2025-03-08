package com.example.mangaapp.Domain.UseCase

import com.example.mangaapp.Data.RepoImp.MangaRepositoryImpl
import com.example.mangaapp.Domain.Entity.Chapter
import com.example.mangaapp.Domain.RepoInterface.MangaRepository
import java.util.ArrayList

class ContinueChaptersUseCase {
    val continueChapters:MangaRepository=MangaRepositoryImpl()
    suspend fun getTheRestOfChapters(mangaId:String,offsetNumber:Int):ArrayList<Chapter>{
    return continueChapters.getMoreMangaChapters(mangaId,offsetNumber)
    }
}