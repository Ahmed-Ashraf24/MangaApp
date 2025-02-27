package com.example.mangaapp.Data.RepoImp

import com.example.mangaapp.Data.DataSource.local.DataBaseClient
import com.example.mangaapp.Data.Models.UserEntity
import com.example.mangaapp.Domain.RepoInterface.registering

class signUp(val user:UserEntity) : registering {
    override fun registerMethod() {
            val dataBaseClient=DataBaseClient()
        dataBaseClient.insertUser(user)
    }
}