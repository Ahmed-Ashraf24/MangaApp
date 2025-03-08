package com.example.mangaapp.Data.Models.APIModel.`ِApiResonse`

data class CoverData(
    val id: String,
    val type: String,
    val attributes: CoverAttributes
)

data class CoverAttributes(
    val fileName: String
)