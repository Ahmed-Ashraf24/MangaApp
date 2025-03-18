package com.example.mangaapp.presentaion.ViewModels.Auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mangaapp.Data.Models.DataBaseModel.UserEntity
import com.example.mangaapp.Domain.UseCase.Auth.RemoteSignUpUseCase
import com.example.mangaapp.Utilities.Constants

class SignUpViewModel :ViewModel() {
    private val _registerState = MutableLiveData<Result<Unit>>()
    val registerState: LiveData<Result<Unit>> = _registerState
    private val _emailState = MutableLiveData<Boolean>()
    val emailState: LiveData<Boolean> get() = _emailState

    fun register(user: UserEntity):Boolean {
        val isEmailValid= Constants.isValidEmail(user.userEmail)
        if(isEmailValid){
           return try {
                RemoteSignUpUseCase(user).signUp()
                _registerState.value = Result.success(Unit)
                true
            } catch (e: Exception) {
                _registerState.value = Result.failure(e)
                false
            }
        }
        else{
            return false
        }
    }
    }
