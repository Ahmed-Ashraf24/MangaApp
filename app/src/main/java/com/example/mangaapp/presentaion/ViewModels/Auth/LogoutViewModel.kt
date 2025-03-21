package com.example.mangaapp.presentaion.ViewModels.Auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mangaapp.Domain.UseCase.Auth.RemoteLogoutUseCase

class LogoutViewModel : ViewModel() {
    private val _logoutState = MutableLiveData<Result<Unit>>()
    val logoutState: LiveData<Result<Unit>> = _logoutState
    private val _emailState = MutableLiveData<Boolean>()
    val emailState: LiveData<Boolean> get() = _emailState

    fun logout(){
            try {
                RemoteLogoutUseCase().logout()
                _logoutState.value = Result.success(Unit)
            } catch (e: Exception) {
                _logoutState.value = Result.failure(e)

            }
        }
    }
