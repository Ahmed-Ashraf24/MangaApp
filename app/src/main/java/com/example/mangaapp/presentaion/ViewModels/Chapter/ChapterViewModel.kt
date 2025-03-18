package com.example.mangaapp.presentaion.ViewModels.Chapter

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mangaapp.Domain.Entity.Chapter
import com.example.mangaapp.Domain.UseCase.Chapter.ChapterUseCase
import com.example.mangaapp.Domain.UseCase.Chapter.ContinueChaptersUseCase
import kotlinx.coroutines.launch

class ChapterViewModel:ViewModel() {
    private val _chapterList = MutableLiveData<ArrayList<Chapter>>()
    val chapterList: LiveData<ArrayList<Chapter>> get() = _chapterList
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage : LiveData<String> get() = _errorMessage
    private val _isChaptersCompleted = MutableLiveData<Boolean>()
    val isChaptersCompleted: LiveData<Boolean> get() = _isChaptersCompleted
    private var offsetCounter = 100


    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading
    fun fetchMangaChapters(mangaId: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _isChaptersCompleted.value=false
            try {
                val chapterData = ChapterUseCase().getAllChapters(mangaId)
                Log.d("manga chapter fetched :",chapterData.toString())
                if(chapterData.size!=100){
                    _isChaptersCompleted.value=true
                }
                _chapterList.value = chapterData
                Log.d("manga chpater fetched size :",chapterList.value!!.size.toString())
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Unknown error"
            } finally {
                _isLoading.value = false
            }
        }
    }
    fun fetchMoreMangaChapters(mangaId: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _isChaptersCompleted.value=false
            try {
                val currentList = _chapterList.value ?: arrayListOf()

                val chapterData = ContinueChaptersUseCase().getTheRestOfChapters(mangaId,offsetCounter)
                Log.d("manga chapter fetched :",chapterData.toString())
                offsetCounter+=100
                if (chapterData.size!=100){
                    _isChaptersCompleted.value=true
                }
                val updatedList = ArrayList(currentList)
                updatedList.addAll(chapterData)
                _chapterList.value=updatedList
                Log.d("manga chapter fetched size :",chapterList.value!!.size.toString())

            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Unknown error"
            } finally {
                _isLoading.value = false
            }
        }
    }
}