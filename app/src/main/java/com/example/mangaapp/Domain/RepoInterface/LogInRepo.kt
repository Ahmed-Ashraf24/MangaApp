package com.example.mangaapp.Domain.RepoInterface

import com.example.mangaapp.Domain.Entity.UserName

interface LogInRepo {
    fun getUserName() : UserName?
}