package com.example.mangaapp.presentaion.ViewModels.Auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mangaapp.Domain.UseCase.Auth.ChangeEmailUseCase
import com.example.mangaapp.Domain.UseCase.Auth.ChangePasswordUseCase
import com.example.mangaapp.Utilities.Constants
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ResetUserViewModel : ViewModel() {
    private val _emailResetStatus = MutableLiveData<Result<Unit>>()
    val emailResetStatus: LiveData<Result<Unit>> get() = _emailResetStatus
    private val _passwordResetStatus = MutableLiveData<Result<Unit>>()
    val passwordResetStatus: LiveData<Result<Unit>> get() = _passwordResetStatus

    fun resetUserEmail(email: String) {
        try {
            if (Constants.isValidEmail(email)){
                viewModelScope.launch {
                    ChangeEmailUseCase().changeUserEmail(email)
                    _emailResetStatus.value=Result.success(Unit)
                }
            }
            else{
                _emailResetStatus.value=Result.failure(Exception("Invalid Email"))
            }

        } catch (e: Exception) {
            _emailResetStatus.value = Result.failure(e)
        }
    }
    fun resetUserPassword(password:String){
        try{
            viewModelScope.launch {
                ChangePasswordUseCase().changeUserPassword(password)
                _emailResetStatus.value=Result.success(Unit)
            }

        }catch (e:Exception){
            _emailResetStatus.value=Result.failure(e)
        }
    }
}