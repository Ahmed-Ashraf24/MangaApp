package com.example.mangaapp.Domain.UseCase.Chapter

import com.example.mangaapp.Data.RepoImp.MangaRepositoryImpl
import com.example.mangaapp.Domain.Entity.Chapter
import com.example.mangaapp.Domain.Entity.ChapterPanels
import com.example.mangaapp.Domain.RepoInterface.MangaRepository

class ChapterPagesUseCase {
    val chapterPage:MangaRepository=MangaRepositoryImpl()
    suspend fun getChapterPages(chapter: Chapter): ChapterPanels {
        return chapterPage.getChapterPanels(chapter)!!
    }
}