package com.example.mangaapp.Data.Mapper

import com.example.mangaapp.Data.Models.APIModel.`ِApiResonse`.ChapterData
import com.example.mangaapp.Domain.Entity.Chapter

class ChapterMapper {
    companion object{
        fun toChapter(chapterData: ChapterData):Chapter{
            return Chapter(
                chapterName = chapterData.attributes.chapter?:"",
                chapterId = chapterData.id
            )
        }
    }
}