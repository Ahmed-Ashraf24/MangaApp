package com.example.mangaapp.Data.RepoImp

import com.example.mangaapp.Data.DataSource.DataBase.DataBaseClient
import com.example.mangaapp.Data.DataSource.DataBase.Remote.RemoteDataBase
import com.example.mangaapp.Data.Models.DataBaseModel.UserEntity
import com.example.mangaapp.Domain.RepoInterface.Registration

class RemoteSignUpImpl(val user: UserEntity) : Registration {
    override fun registerationMethod() {
        val remoteDataBaseClient :     DataBaseClient =    RemoteDataBase()
        remoteDataBaseClient.saveUser(user)
    }
}