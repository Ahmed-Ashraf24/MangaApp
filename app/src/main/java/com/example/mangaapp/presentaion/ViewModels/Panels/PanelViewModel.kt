package com.example.mangaapp.presentaion.ViewModels.Panels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mangaapp.Domain.Entity.Chapter
import com.example.mangaapp.Domain.Entity.ChapterPanels
import com.example.mangaapp.Domain.UseCase.Chapter.ChapterPagesUseCase
import kotlinx.coroutines.launch

class PanelViewModel:ViewModel() {
    private val _chapterPanelURLList= MutableLiveData<ChapterPanels>()
    val chapterPanelURLList: LiveData<ChapterPanels> get()=_chapterPanelURLList
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage : LiveData<String> get() = _errorMessage

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun fetchChapterPanels(chapter: Chapter) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val chapterPagesData = ChapterPagesUseCase().getChapterPages(chapter)
                chapterPagesData.let {
                    _chapterPanelURLList.value=it
                }
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Unknown error"
            } finally {
                _isLoading.value = false
            }
        }
    }
}