package com.example.mangaapp.Data.RepoImp

import com.example.mangaapp.Data.DataSource.DataBase.DataBaseClient
import com.example.mangaapp.Data.DataSource.DataBase.Remote.RemoteDataBase
import com.example.mangaapp.Domain.RepoInterface.AccountRepo

class RemoteAccountImpl : AccountRepo {
    val remoteDataBaseClient: DataBaseClient = RemoteDataBase()
    override suspend fun changeEmail(email: String) {
        remoteDataBaseClient.changeUserEmail(email)
    }

    override suspend fun changePassword(password: String) {
        remoteDataBaseClient.changUserPassword(password)

    }
}