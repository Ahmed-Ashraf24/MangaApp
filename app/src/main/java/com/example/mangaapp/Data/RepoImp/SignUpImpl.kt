package com.example.mangaapp.Data.RepoImp

import com.example.mangaapp.Data.DataSource.DataBaseClient
import com.example.mangaapp.Data.DataSource.local.LocalDataBase
import com.example.mangaapp.Data.Models.UserEntity
import com.example.mangaapp.Domain.RepoInterface.Registration

class SignUpImpl(val user:UserEntity) : Registration {
    override fun registerationMethod() {
        val LocalDataBaseClient : DataBaseClient=LocalDataBase()
        LocalDataBaseClient.saveUser(user)
    }
}