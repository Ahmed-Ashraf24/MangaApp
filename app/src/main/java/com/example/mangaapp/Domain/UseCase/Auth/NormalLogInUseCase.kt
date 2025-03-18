package com.example.mangaapp.Domain.UseCase.Auth

import com.example.mangaapp.Data.RepoImp.NormalLogInImpl
import com.example.mangaapp.Domain.Entity.User
import com.example.mangaapp.Domain.RepoInterface.LogInRepo

class NormalLogInUseCase(val email: String, val password: String) {
    suspend fun excute(): User? {
        val normalLogIn: LogInRepo = NormalLogInImpl(email, password)
        return normalLogIn.getUserName()
    }
}