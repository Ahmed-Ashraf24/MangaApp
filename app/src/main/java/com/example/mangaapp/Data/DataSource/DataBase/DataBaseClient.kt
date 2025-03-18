package com.example.mangaapp.Data.DataSource.DataBase

import com.example.mangaapp.Data.Models.DataBaseModel.UserEntity

interface DataBaseClient{

 fun saveUser(user: UserEntity)
 suspend fun getUser(email:String,password:String) : UserEntity?
 suspend fun addMangaToFav(MangaId:String)
 suspend fun addToHistory(MangaId:String)
 fun logout()
 suspend fun changeUserEmail(email : String)
 suspend fun changUserPassword(password: String)


}