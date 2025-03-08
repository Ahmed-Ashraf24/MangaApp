package com.example.mangaapp.Data.Models.DataBaseModel

data class RemoteUser(
val userName: String = "",
val userEmail: String = "",
val userAge: Int = 0,
val favManga: List<HashMap<String, String>> = emptyList()
)

