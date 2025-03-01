package com.example.mangaapp.Domain.UseCase

import com.example.mangaapp.Data.RepoImp.NormalLogInImpl
import com.example.mangaapp.Domain.Entity.UserName
import com.example.mangaapp.Domain.RepoInterface.LogInRepo

class NormalLogInUseCase(val email: String, val password: String) {
    fun excute(): UserName? {
        val normalLogIn: LogInRepo = NormalLogInImpl(email, password)
        return normalLogIn.getUserName()
    }
}