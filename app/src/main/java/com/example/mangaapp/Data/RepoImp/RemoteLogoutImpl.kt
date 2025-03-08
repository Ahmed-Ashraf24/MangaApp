package com.example.mangaapp.Data.RepoImp

import com.example.mangaapp.Data.DataSource.DataBase.DataBaseClient
import com.example.mangaapp.Data.DataSource.DataBase.Remote.RemoteDataBase
import com.example.mangaapp.Domain.RepoInterface.LogOutRepo

class RemoteLogoutImpl:LogOutRepo {
    val remoteDatabaseClient:DataBaseClient=RemoteDataBase()
    override fun logout() {
        remoteDatabaseClient.logout()
    }
}