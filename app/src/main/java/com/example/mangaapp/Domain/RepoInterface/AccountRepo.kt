package com.example.mangaapp.Domain.RepoInterface

interface AccountRepo {
    suspend fun changeEmail(email:String)
    suspend fun changePassword(password:String)
}