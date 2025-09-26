package com.example.mangaapp.Domain.UseCase.Auth

import com.example.mangaapp.Data.RepoImp.LogoutImpl
import com.example.mangaapp.Domain.RepoInterface.LogOutRepo

class LogoutUseCase(private val logOutRepo: LogOutRepo) {
    fun logout(){
        logOutRepo.logout()
    }
}