package com.example.mangaapp.Domain.RepoInterface
import com.example.mangaapp.Domain.Entity.Chapter
import com.example.mangaapp.Domain.Entity.ChapterPanels
import com.example.mangaapp.Domain.Entity.Manga

interface MangaRepository {
   suspend fun getLatestManga(): ArrayList<Manga>
   suspend fun getPopularManga(): ArrayList<Manga>
   suspend fun getRecommendedManga(): ArrayList<Manga>
   suspend fun getMangaByTitle(mangaTitle:String):ArrayList<Manga>
   suspend fun getMangaByItsGenre(mangaGenre:String):ArrayList<Manga>
   suspend fun getMangaChapters(mangaId:String):ArrayList<Chapter>
   suspend fun getMoreMangaChapters(mangaId:String,offsetNumber:Int):ArrayList<Chapter>
   suspend fun getChapterPanels(chapter:Chapter):ChapterPanels?
   suspend fun getMangaDetails(mangaId:String):Manga?

}