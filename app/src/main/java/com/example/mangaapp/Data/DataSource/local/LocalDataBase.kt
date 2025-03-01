package com.example.mangaapp.Data.DataSource.local

import com.example.mangaapp.Data.DataSource.DataBaseClient
import com.example.mangaapp.Data.Models.UserDataBase
import com.example.mangaapp.Data.Models.UserEntity

class LocalDataBase:DataBaseClient {
    private val dataBaseInstance= UserDataBase.getDataBaseInstant()
    private val dao=dataBaseInstance.getUserDAO()
    override fun saveUser(user: UserEntity) {
        dao.insertUser(user)
    }

    override fun getUser(email: String, password: String): UserEntity? {
        return dao.getUser(email,password)
    }


}