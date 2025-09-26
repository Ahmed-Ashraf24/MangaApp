package com.example.mangaapp.Domain.UseCase.Auth

import com.example.mangaapp.Domain.RepoInterface.Registration

class SignUpUseCase(private val register:Registration) {
    fun signUp(){
        register.registerationMethod()
    }
}