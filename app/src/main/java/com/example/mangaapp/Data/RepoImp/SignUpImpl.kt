package com.example.mangaapp.Data.RepoImp

import com.example.mangaapp.Data.DataSource.DataBase.DataBaseClient
import com.example.mangaapp.Data.DataSource.DataBase.Local.LocalDataBase
import com.example.mangaapp.Data.Models.DataBaseModel.UserEntity
import com.example.mangaapp.Domain.RepoInterface.Registration

class SignUpImpl(val user: UserEntity,private val dataBaseClient: DataBaseClient) : Registration {
    override fun registerationMethod() {
        dataBaseClient.saveUser(user)
    }
}