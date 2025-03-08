package com.example.mangaapp.Domain.UseCase

import com.example.mangaapp.Data.RepoImp.RemoteLogoutImpl
import com.example.mangaapp.Domain.RepoInterface.LogOutRepo

class RemoteLogoutUseCase {
    val remoteLogout:LogOutRepo= RemoteLogoutImpl()
    fun logout(){
        remoteLogout.logout()
    }
}