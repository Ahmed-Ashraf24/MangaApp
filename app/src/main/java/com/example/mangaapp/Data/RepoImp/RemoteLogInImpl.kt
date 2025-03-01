package com.example.mangaapp.Data.RepoImp

import android.util.Log
import com.example.mangaapp.Data.DataSource.DataBaseClient
import com.example.mangaapp.Data.DataSource.remote.RemoteDataBase
import com.example.mangaapp.Data.Mapper.UserNameMapper
import com.example.mangaapp.Domain.Entity.UserName
import com.example.mangaapp.Domain.RepoInterface.LogInRepo

class RemoteLogInImpl(val email:String,val password:String):LogInRepo {
    val remoteDataBaseClient:DataBaseClient=RemoteDataBase()

    override fun getUserName(): UserName? {
        return try{

            remoteDataBaseClient.getUser(email, password)!!.let { UserNameMapper.toUserName(it) }

        }catch (e:Exception){
            Log.e("DatabaseError", "Login failed: ${e.message}")
            null

        }    }
}