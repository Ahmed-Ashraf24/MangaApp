package com.example.mangaapp.Domain.UseCase.Auth

import com.example.mangaapp.Domain.Entity.User
import com.example.mangaapp.Domain.RepoInterface.LogInRepo

class LogInUseCase(val email: String, val password: String, val logInRepo: LogInRepo) {
    suspend fun excute(): User? {

        return logInRepo.getUser(email,password)
    }
}