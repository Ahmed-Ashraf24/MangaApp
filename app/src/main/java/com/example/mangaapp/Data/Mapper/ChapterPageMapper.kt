package com.example.mangaapp.Data.Mapper

import com.example.mangaapp.Data.Models.APIModel.`ِApiResonse`.ChapterData
import com.example.mangaapp.Domain.Entity.Chapter
import com.example.mangaapp.Domain.Entity.ChapterPanels

class ChapterPageMapper {
    companion object {
        fun toChapterPanels(chapter: Chapter, panelsUrl:ArrayList<String>): ChapterPanels {
            return ChapterPanels(
                chapterID = chapter.chapterId,
                chapterName = chapter.chapterName,
                chapterPanelsURL = panelsUrl
            )

        }
    }
}