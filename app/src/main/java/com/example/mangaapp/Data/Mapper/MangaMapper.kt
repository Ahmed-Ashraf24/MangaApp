package com.example.mangaapp.Data.Mapper

import com.example.mangaapp.Domain.Entity.Manga

class MangaMapper {
    companion object {
        fun toManga(mangaData: com.example.mangaapp.Data.Models.APIModel.`ِApiResonse`.MangaData, imageUrl:String):Manga{
            return Manga(
                id=mangaData.id,
                name = mangaData.attributes.title.get("en")?: mangaData.attributes.title.get("ja-ro")?:"no title"
                , description = mangaData.attributes.description!!.get("en")?:"no description"
                , imageUrl = imageUrl,
                genres = mangaData.attributes.tags
                    .filter { it.attributes.group == "genre" }
                    .mapNotNull { it.attributes.name["en"] }
                    .joinToString(", ")
            )

        }
        fun toManga(mangaData: com.example.mangaapp.Data.Models.APIModel.Response.MangaData, imageUrl:String):Manga{
            return Manga(
                id=mangaData.id,
                name = mangaData.attributes.title.get("en")?: mangaData.attributes.title.get("ja-ro")?: "No Title"
                , description = mangaData.attributes.description!!.get("en")?:"no description"
                , imageUrl = imageUrl,
                genres = mangaData.attributes.tags
                    .filter { it.attributes.group == "genre" }
                    .mapNotNull { it.attributes.name["en"] }
                    .joinToString(", ")
            )
        }

    }
}