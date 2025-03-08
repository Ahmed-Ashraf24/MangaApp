package com.example.mangaapp.Data.Models.APIModel.Response

data class MangaDetailResponse(
    val data: MangaData
)

data class MangaData(
    val id: String,
    val type: String,
    val attributes: MangaAttributes,
    val relationships: List<MangaRelationship>
)

data class MangaAttributes(
    val title: Map<String, String>,
    val description: Map<String, String>,
    val tags: List<Tag>,
    val status: String?,
    val year: Int?,
    val contentRating: String?,
    val createdAt: String?,
    val updatedAt: String?
)

data class Tag(
    val id: String,
    val type: String,
    val attributes: TagAttributes
)

data class TagAttributes(
    val name: Map<String, String>,
    val description: Map<String, String>,
    val group: String,
    val version: Int
)

data class MangaRelationship(
    val id: String,
    val type: String,
    val attributes: RelationshipAttributes?
)

data class RelationshipAttributes(
    val fileName: String?
)
