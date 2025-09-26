package com.example.mangaapp.Domain.UseCase.Auth

import com.example.mangaapp.Data.RepoImp.AccountImpl
import com.example.mangaapp.Domain.RepoInterface.AccountRepo

class ChangePasswordUseCase(private val accountRepo: AccountRepo) {
    suspend fun changeUserPassword(password:String){
        accountRepo.changePassword(password)
    }
}