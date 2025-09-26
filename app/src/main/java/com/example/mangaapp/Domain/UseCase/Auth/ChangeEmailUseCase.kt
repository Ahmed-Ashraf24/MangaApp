package com.example.mangaapp.Domain.UseCase.Auth

import com.example.mangaapp.Data.DataSource.DataBase.Remote.RemoteDataBase
import com.example.mangaapp.Data.RepoImp.AccountImpl
import com.example.mangaapp.Domain.RepoInterface.AccountRepo

class ChangeEmailUseCase(private val accountRepo: AccountRepo) {
    suspend fun changeUserEmail(email:String){
        accountRepo.changeEmail(email)
    }
}