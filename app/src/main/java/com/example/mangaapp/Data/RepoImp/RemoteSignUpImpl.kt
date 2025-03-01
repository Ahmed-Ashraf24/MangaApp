package com.example.mangaapp.Data.RepoImp

import com.example.mangaapp.Data.DataSource.DataBaseClient
import com.example.mangaapp.Data.DataSource.local.LocalDataBase
import com.example.mangaapp.Data.DataSource.remote.RemoteDataBase
import com.example.mangaapp.Data.Models.UserEntity
import com.example.mangaapp.Domain.RepoInterface.Registration

class RemoteSignUpImpl(val user: UserEntity) : Registration {
    override fun registerationMethod() {
        val remoteDataBaseClient : DataBaseClient = RemoteDataBase()
        remoteDataBaseClient.saveUser(user)
    }
}