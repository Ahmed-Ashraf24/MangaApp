package com.example.mangaapp.Domain.UseCase.Auth

import com.example.mangaapp.Data.RepoImp.RemoteAccountImpl
import com.example.mangaapp.Domain.RepoInterface.AccountRepo

class ChangePasswordUseCase {
    val remoteAccount: AccountRepo = RemoteAccountImpl()
    suspend fun changeUserPassword(password:String){
        remoteAccount.changePassword(password)
    }
}