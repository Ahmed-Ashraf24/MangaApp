package com.example.mangaapp.Domain.RepoInterface

import com.example.mangaapp.Domain.Entity.User

interface LogInRepo {
    suspend fun getUser(email: String, password: String) : User?
}