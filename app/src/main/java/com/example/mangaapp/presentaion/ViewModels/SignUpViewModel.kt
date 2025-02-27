package com.example.mangaapp.presentaion.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mangaapp.Data.Models.UserEntity
import com.example.mangaapp.Domain.UseCase.SignUpUseCase

class SignUpViewModel :ViewModel() {
    private val _registerState = MutableLiveData<Result<Unit>>()
    val registerState: LiveData<Result<Unit>> = _registerState

    fun register(user: UserEntity) {
            try {
                SignUpUseCase(user).signUp()
                _registerState.value = Result.success(Unit)
            } catch (e: Exception) {
                _registerState.value = Result.failure(e)
            }
        }
    }
