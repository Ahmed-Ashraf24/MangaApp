package com.example.mangaapp.Data.Models

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface userdao {
    @Query("SELECT * FROM users_table WHERE userEmail = :email AND userPassword = :password")
    fun getUser(email: String, password: String): UserEntity?
    @Insert
    fun insertUser(User:UserEntity)
}