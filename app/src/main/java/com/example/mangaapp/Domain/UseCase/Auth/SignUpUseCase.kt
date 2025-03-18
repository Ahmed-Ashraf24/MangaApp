package com.example.mangaapp.Domain.UseCase.Auth

import com.example.mangaapp.Data.Models.DataBaseModel.UserEntity
import com.example.mangaapp.Data.RepoImp.SignUpImpl
import com.example.mangaapp.Domain.RepoInterface.Registration

class SignUpUseCase(user : UserEntity) {
    val signUp:Registration=SignUpImpl(user)
    fun signUp(){
        signUp.registerationMethod()
    }
}