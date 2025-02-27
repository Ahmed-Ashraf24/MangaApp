package com.example.mangaapp.Data.RepoImp

import android.util.Log
import com.example.mangaapp.Data.DataSource.local.DataBaseClient
import com.example.mangaapp.Data.Models.UserEntity
import com.example.mangaapp.Data.Models.toUserName
import com.example.mangaapp.Domain.Entity.UserName
import com.example.mangaapp.Domain.RepoInterface.LogIn

class NormalLogIn(val email:String,val password:String)  : LogIn {
    val dataBaseClient=DataBaseClient()
    override fun LoginMethod(): UserName? {
        return try{
            Log.e("DatabaseError",dataBaseClient.getUser(email, password)!!.toString())

            dataBaseClient.getUser(email, password)!!.let { toUserName(it) }

        }catch (e:Exception){
            Log.e("DatabaseError", "Login failed: ${e.message}")

            null

        }
    }


}