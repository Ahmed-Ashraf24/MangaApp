package com.example.mangaapp.Domain.Entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Manga(
    val id: String,
    val name: String,
    val description: String,
    val imageUrl: String,
    val genres :String
) : Parcelable