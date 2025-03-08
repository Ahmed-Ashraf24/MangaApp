package com.example.mangaapp.Data.Models.APIModel.`ِApiResonse`

data class MangaData(val id: String,
                     val type: String,
                     val attributes: MangaAttributes,
                     val relationships: List<MangaRelationship>)

data class MangaRelationship( val id: String,
                              val type: String)

data class MangaAttributes(
    val title: Map<String, String>,
    val description: Map<String, String>?,
    val tags: List<Tag>
)
data class Tag(
    val attributes: TagAttributes
)
data class TagAttributes(
    val name: Map<String, String>,
    val group: String
)