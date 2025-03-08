package com.example.mangaapp.Domain.UseCase

import com.example.mangaapp.Data.Models.DataBaseModel.UserEntity
import com.example.mangaapp.Data.RepoImp.RemoteSignUpImpl
import com.example.mangaapp.Domain.RepoInterface.Registration

class RemoteSignUpUseCase(user : UserEntity) {
    val remoteSignUp: Registration = RemoteSignUpImpl(user)
    fun signUp(){
        remoteSignUp.registerationMethod()
    }
}