package com.example.mangaapp.presentaion.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mangaapp.Data.Models.UserEntity
import com.example.mangaapp.Domain.Entity.UserName
import com.example.mangaapp.Domain.UseCase.NormalLogInUseCase
import kotlinx.coroutines.launch

class LogInViewModel :ViewModel() {
    private val _loginState = MutableLiveData<Result<UserName>>()
    val loginState: LiveData<Result<UserName>> = _loginState
    fun login(email: String, password: String) :String ?{

            try {
                val user = NormalLogInUseCase(email,password).excute()
                _loginState.value = Result.success(user!!)
                return user.name
            } catch (e: Exception) {
                _loginState.value = Result.failure(e)
                return null

            }
    }
    }

