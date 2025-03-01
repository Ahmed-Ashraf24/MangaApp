package com.example.mangaapp.Domain.UseCase

import com.example.mangaapp.Data.RepoImp.NormalLogInImpl
import com.example.mangaapp.Data.RepoImp.RemoteLogInImpl
import com.example.mangaapp.Domain.Entity.UserName
import com.example.mangaapp.Domain.RepoInterface.LogInRepo

class RemoteLogInUseCase(val email: String, val password: String) {
    fun excute(): UserName? {
        val remoteLogIn: LogInRepo = RemoteLogInImpl(email, password)
        return remoteLogIn.getUserName()
    }
}