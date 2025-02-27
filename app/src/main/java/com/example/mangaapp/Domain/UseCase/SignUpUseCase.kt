package com.example.mangaapp.Domain.UseCase

import com.example.mangaapp.Data.Models.UserEntity
import com.example.mangaapp.Data.RepoImp.signUp
import com.example.mangaapp.Domain.RepoInterface.registering

class SignUpUseCase(user :UserEntity) {
    val registering:registering=signUp(user)
    fun signUp(){
        registering.registerMethod()
    }
}