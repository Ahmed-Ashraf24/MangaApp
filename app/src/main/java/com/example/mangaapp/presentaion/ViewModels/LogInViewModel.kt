package com.example.mangaapp.presentaion.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mangaapp.Data.Models.UserEntity
import com.example.mangaapp.Data.RepoImp.RemoteLogInImpl
import com.example.mangaapp.Domain.Entity.UserName
import com.example.mangaapp.Domain.UseCase.NormalLogInUseCase
import com.example.mangaapp.Domain.UseCase.RemoteLogInUseCase
import com.example.mangaapp.Utilities.Constants
import kotlinx.coroutines.launch

class LogInViewModel :ViewModel() {
    private val _loginState = MutableLiveData<Result<UserName>>()
    val loginState: LiveData<Result<UserName>> = _loginState
    private val _emailState = MutableLiveData<Boolean>()
    val emailState: LiveData<Boolean> get() = _emailState

    fun login(email: String, password: String) :String ?{
            val isEmailValid=Constants.isValidEmail(email)
        if(isEmailValid){
            try {
                val user  = RemoteLogInUseCase(email,password).excute()!!
                _loginState.value = Result.success(user)
                return user.name
            } catch (e: Exception) {
                _loginState.value = Result.failure(e)
                return null

            }}
        else{
            return "Invalid Email"
        }
    }
    }

