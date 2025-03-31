package com.example.mangaapp.Data.DataSource.DataBase.Local

import com.example.mangaapp.Data.DataSource.DataBase.DataBaseClient
import com.example.mangaapp.Data.Models.DataBaseModel.UserDataBase
import com.example.mangaapp.Data.Models.DataBaseModel.UserEntity

class LocalDataBase: DataBaseClient {
    private val dataBaseInstance= UserDataBase.getDataBaseInstant()
    private val dao=dataBaseInstance.getUserDAO()
    override fun saveUser(user: UserEntity) {
        dao.insertUser(user)
    }

    override suspend fun getUser(email: String, password: String): UserEntity? {
        return dao.getUser(email,password)
    }

    override suspend fun addMangaToFav(MangaId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun addToHistory(MangaId: String) {
        TODO("Not yet implemented")
    }

    override fun logout() {
        TODO("Not yet implemented")
    }

    override suspend fun changeUserEmail(email: String) {
        TODO("Not yet implemented")
    }



    override suspend fun changUserPassword(password: String) {
        TODO("Not yet implemented")
    }


}