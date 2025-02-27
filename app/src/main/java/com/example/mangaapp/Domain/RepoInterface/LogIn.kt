package com.example.mangaapp.Domain.RepoInterface

import com.example.mangaapp.Data.Models.UserEntity
import com.example.mangaapp.Domain.Entity.UserName

interface LogIn {
    fun LoginMethod() : UserName?
}