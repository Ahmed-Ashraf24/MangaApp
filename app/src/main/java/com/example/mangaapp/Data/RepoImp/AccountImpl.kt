package com.example.mangaapp.Data.RepoImp

import com.example.mangaapp.Data.DataSource.DataBase.DataBaseClient
import com.example.mangaapp.Domain.RepoInterface.AccountRepo

class AccountImpl(private val dataBaseClient: DataBaseClient) : AccountRepo {
    override suspend fun changeEmail(email: String) {
        dataBaseClient.changeUserEmail(email)
    }

    override suspend fun changePassword(password: String) {
        dataBaseClient.changUserPassword(password)

    }
}