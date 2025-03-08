package com.example.mangaapp.Data.RepoImp

import android.util.Log
import com.example.mangaapp.Data.DataSource.DataBase.DataBaseClient
import com.example.mangaapp.Data.DataSource.DataBase.Remote.RemoteDataBase
import com.example.mangaapp.Data.Mapper.UserMapper
import com.example.mangaapp.Domain.Entity.User
import com.example.mangaapp.Domain.RepoInterface.LogInRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoteLogInImpl(val email: String, val password: String) : LogInRepo {
    val remoteDataBaseClient: DataBaseClient = RemoteDataBase()

    override suspend fun getUserName(): User? {
        return try {

            return withContext(Dispatchers.IO) {
                remoteDataBaseClient.getUser(email, password)?.let { UserMapper.toUser(it) }
            }
        } catch (e: Exception) {
            Log.e("DatabaseError", "Login failed: ${e.message}")
            null

        }
    }
}