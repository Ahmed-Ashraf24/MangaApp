package com.example.mangaapp.Data.RepoImp

import android.util.Log
import com.example.mangaapp.Data.DataSource.DataBase.DataBaseClient
import com.example.mangaapp.Data.Mapper.UserMapper
import com.example.mangaapp.Domain.Entity.User
import com.example.mangaapp.Domain.RepoInterface.LogInRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LogInImpl(val dataBaseClient: DataBaseClient) : LogInRepo {


    override suspend fun getUser(email: String, password: String): User? {
        return try {

            return withContext(Dispatchers.IO) {
               dataBaseClient.getUser(email, password)?.let { UserMapper.toUser(it) }
            }
        } catch (e: Exception) {
            Log.e("DatabaseError", "Login failed: ${e.message}")
            null

        }
    }
}