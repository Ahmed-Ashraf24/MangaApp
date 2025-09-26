package com.example.mangaapp.presentaion.ViewModels.Manga

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mangaapp.Data.DataSource.DataBase.Remote.RemoteDataBase
import com.example.mangaapp.Data.RepoImp.FavoriteImpl
import com.example.mangaapp.Data.RepoImp.HistoryImpl
import com.example.mangaapp.Data.RepoImp.MangaRepositoryImpl
import com.example.mangaapp.Domain.Entity.Manga
import com.example.mangaapp.Domain.UseCase.Manga.FavMangaUseCase
import com.example.mangaapp.Domain.UseCase.Manga.GenreMangaUseCase
import com.example.mangaapp.Domain.UseCase.Manga.HistoryMangaUseCase
import com.example.mangaapp.Domain.UseCase.Manga.LatestMangaUseCase
import com.example.mangaapp.Domain.UseCase.Manga.PopularMangaUseCase
import com.example.mangaapp.Domain.UseCase.Manga.SearchMangaUseCase
import com.example.mangaapp.Utilities.Constants
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MangaViewModel : ViewModel() {

    private val _searchedMangaList = MutableLiveData<ArrayList<Manga>>()
    val searchedMangaList: LiveData<ArrayList<Manga>> get() = _searchedMangaList

    private val _latestMangaList by lazy { MutableLiveData<ArrayList<Manga>>() }
    val latestMangaList: LiveData<ArrayList<Manga>> get() = _latestMangaList

    private val _popularMangaList by lazy { MutableLiveData<ArrayList<Manga>>() }
    val popularMangaList: LiveData<ArrayList<Manga>> get() = _popularMangaList

    private val _recommendedMangaList by lazy { MutableLiveData<ArrayList<Manga>>() }
    val recommendedMangaList: LiveData<ArrayList<Manga>> get() = _recommendedMangaList

    private val _favMangaList by lazy { MutableLiveData<ArrayList<Manga>>() }
    val favMangaList: LiveData<ArrayList<Manga>> get() = _favMangaList

    private val _histMangaList by lazy { MutableLiveData<ArrayList<Manga>>() }
    val histMangaList: LiveData<ArrayList<Manga>> get() = _histMangaList

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun fetchMangaList() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val recommendedMangaData =
                    GenreMangaUseCase().getMangaByGenre(Constants.GENRES.getValue("Action"))
                _recommendedMangaList.value = recommendedMangaData
                delay(500)

                val latestMangaData = LatestMangaUseCase().getAllManga()
                _latestMangaList.value = latestMangaData

                delay(500)
                val popularMangaData = PopularMangaUseCase().getAllManga()
                _popularMangaList.value = popularMangaData
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Unknown error"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun fetchSearchedManga(mangaTitle: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val mangaData = SearchMangaUseCase().getMangaByTitle(mangaTitle)
                Log.d("Searched manga :", mangaData.toString())
                _searchedMangaList.value = mangaData
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Unknown error"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun fetchLatestManga(mangaGenre: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val mangaData = GenreMangaUseCase().getMangaByGenre(mangaGenre)
                _latestMangaList.value = mangaData
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Unknown error"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun fetchMangaByItsGenre(mangaGenre: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val mangaData = GenreMangaUseCase().getMangaByGenre(mangaGenre)
                _searchedMangaList.value = mangaData
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Unknown error"
            } finally {
                _isLoading.value = false
            }
        }
    }


    fun addMangaToFavList(manga: Manga) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val currentFavList = _favMangaList.value ?: arrayListOf()

                FavMangaUseCase(
                    FavoriteImpl(RemoteDataBase()),
                    MangaRepositoryImpl()
                ).addMangaToFavList(mangaId = manga.id)
                currentFavList!!.add(manga)
                val updatedFavMangaList = ArrayList<Manga>()
                updatedFavMangaList.addAll(currentFavList)
                Log.d(
                    "fav manga data list after update from manga viewmodel",
                    updatedFavMangaList.toString()
                )

                _favMangaList.value = updatedFavMangaList!!
                Log.d("the main fav manga list data from viewmodel", _favMangaList.value.toString())

            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Unknown error"
            } finally {
                _isLoading.value = false
            }
        }
    }

    suspend fun getMangaFromIdForFav(mangaId: String) {
        _isLoading.value = true
        try {
            val currentFavList = _favMangaList.value ?: arrayListOf()
            Log.d("fav manga list before any thing", currentFavList.size.toString())

            val manga = FavMangaUseCase(
                FavoriteImpl(RemoteDataBase()),
                MangaRepositoryImpl()
            ).getMangaFromId(mangaId = mangaId)
            currentFavList!!.add(manga)
            Log.d(" current fav manga list after adding manga", currentFavList.size.toString())

            val updatedFavMangaList = ArrayList<Manga>()
            updatedFavMangaList.addAll(currentFavList)
            Log.d(
                "updated fav manga list after adding current data to it ",
                updatedFavMangaList.size.toString()
            )
            _favMangaList.value = updatedFavMangaList!!
            Log.d("fav manga list after adding a new manga", _favMangaList.value!!.size.toString())
        } catch (e: Exception) {
            Log.d("fav manga data from viewmodel", e.message.toString())
            _errorMessage.value = e.message ?: "Unknown error"
        } finally {
            _isLoading.value = false
        }


    }

    suspend fun getMangaFromIdForHistory(mangaId: String) {
        _isLoading.value = true
        try {
            val currentFavList = _histMangaList.value ?: arrayListOf()
            Log.d("fav manga list before any thing", currentFavList.size.toString())

            val manga = HistoryMangaUseCase(
                HistoryImpl(RemoteDataBase()),
                MangaRepositoryImpl()
            ).getMangaFromId(mangaId = mangaId)
            currentFavList!!.add(manga)
            Log.d(" current fav manga list after adding manga", currentFavList.size.toString())

            val updatedFavMangaList = ArrayList<Manga>()
            updatedFavMangaList.addAll(currentFavList)
            Log.d(
                "updated fav manga list after adding current data to it ",
                updatedFavMangaList.size.toString()
            )
            _histMangaList.value = updatedFavMangaList!!
            Log.d("fav manga list after adding a new manga", _favMangaList.value!!.size.toString())
        } catch (e: Exception) {
            Log.d("fav manga data from viewmodel", e.message.toString())
            _errorMessage.value = e.message ?: "Unknown error"
        } finally {
            _isLoading.value = false
        }


    }

    fun addMangaToHistList(manga: Manga) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val currentHistList = _histMangaList.value ?: arrayListOf()
                HistoryMangaUseCase(
                    HistoryImpl(RemoteDataBase()),
                    MangaRepositoryImpl()
                ).addMangaToHistoryList(mangaId = manga.id)
                currentHistList!!.add(manga)
                val updatedFavMangaList = ArrayList<Manga>()
                updatedFavMangaList.addAll(currentHistList)
                Log.d(
                    "fav manga data list after update from manga viewmodel",
                    updatedFavMangaList.toString()
                )

                _histMangaList.value = updatedFavMangaList!!
                Log.d("the main fav manga list data from viewmodel", _favMangaList.value.toString())
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Unknown error"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun updateFavMangaList(manga: Manga) {
        val currentFavList = _favMangaList.value ?: arrayListOf()
        currentFavList!!.add(manga)
        val updatedFavMangaList = ArrayList<Manga>()
        updatedFavMangaList.addAll(currentFavList)
        _favMangaList.value = updatedFavMangaList
    }

}