package com.example.mangaapp.Data.RepoImp

import android.util.Log
import com.example.mangaapp.Data.DataSource.DataBaseClient
import com.example.mangaapp.Data.DataSource.local.LocalDataBase
import com.example.mangaapp.Data.Mapper.UserNameMapper
import com.example.mangaapp.Domain.Entity.UserName
import com.example.mangaapp.Domain.RepoInterface.LogInRepo

class NormalLogInImpl(val email:String, val password:String)  : LogInRepo {
    val LocalDataBaseClient: DataBaseClient=LocalDataBase()
    override fun getUserName(): UserName? {
        return try{

            LocalDataBaseClient.getUser(email, password)!!.let { UserNameMapper.toUserName(it) }

        }catch (e:Exception){
            Log.e("DatabaseError", "Login failed: ${e.message}")
            null

        }
    }


}