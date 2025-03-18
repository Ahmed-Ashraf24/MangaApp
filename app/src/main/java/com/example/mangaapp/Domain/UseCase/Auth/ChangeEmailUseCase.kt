package com.example.mangaapp.Domain.UseCase.Auth

import com.example.mangaapp.Data.RepoImp.RemoteAccountImpl
import com.example.mangaapp.Domain.RepoInterface.AccountRepo

class ChangeEmailUseCase {
    val remoteAccount:AccountRepo=RemoteAccountImpl()
    suspend fun changeUserEmail(email:String){
        remoteAccount.changeEmail(email)
    }
}