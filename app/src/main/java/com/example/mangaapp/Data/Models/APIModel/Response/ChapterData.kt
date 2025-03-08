package com.example.mangaapp.Data.Models.APIModel.`ِApiResonse`

data class ChapterData(val id: String,
                       val type: String,
                       val attributes: ChapterAttributes
)

data class ChapterAttributes( val title: String?,
                              val volume: String?,
                              val chapter: String?,
                              val translatedLanguage: String)

