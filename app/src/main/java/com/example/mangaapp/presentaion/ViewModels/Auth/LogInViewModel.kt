package com.example.mangaapp.presentaion.ViewModels.Auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mangaapp.Data.DataSource.DataBase.Remote.RemoteDataBase
import com.example.mangaapp.Data.RepoImp.LogInImpl
import com.example.mangaapp.Domain.Entity.User
import com.example.mangaapp.Domain.UseCase.Auth.LogInUseCase
import kotlinx.coroutines.launch

class LogInViewModel : ViewModel() {

    private val _loginState = MutableLiveData<Result<User>>()
    val loginState: LiveData<Result<User>> = _loginState

    private val _emailState = MutableLiveData<Boolean>()
    val emailState: LiveData<Boolean> get() = _emailState

    private val _favMangaList = MutableLiveData<ArrayList<String>>()
    val favMangaList: LiveData<ArrayList<String>> get() = _favMangaList


    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _isLoading.value = true

            try {
                val user = LogInUseCase(email, password,LogInImpl(RemoteDataBase())).excute()
                val favlist=ArrayList<String>()
                favlist.addAll(user!!.favManga)
                _favMangaList.value=favlist
                if (user != null) {
                    _loginState.value = Result.success(user)
                } else {
                    _loginState.value = Result.failure(Exception("Invalid email or password"))
                }
            } catch (e: Exception) {
                _loginState.value = Result.failure(e)
            } finally {
                _isLoading.value = false
            }
        }
    }
}
