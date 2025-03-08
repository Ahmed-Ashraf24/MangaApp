package com.example.mangaapp.Domain.Entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val name: String,
    val favManga: List<String>,
    val email: String,
    val histManga: List<String>
) : Parcelable
