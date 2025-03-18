package com.example.mangaapp.Domain.UseCase.Auth

import com.example.mangaapp.Data.RepoImp.RemoteLogInImpl
import com.example.mangaapp.Domain.Entity.User
import com.example.mangaapp.Domain.RepoInterface.LogInRepo

class RemoteLogInUseCase(val email: String, val password: String) {
    suspend fun excute(): User? {
        val remoteLogIn: LogInRepo = RemoteLogInImpl(email, password)
        return remoteLogIn.getUserName()
    }
}