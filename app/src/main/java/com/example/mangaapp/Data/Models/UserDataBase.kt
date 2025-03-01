package com.example.mangaapp.Data.Models

import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import com.example.mangaapp.MyApplication

@Database(entities = [UserEntity::class], version = 1)
abstract class UserDataBase: RoomDatabase() {
    abstract fun getUserDAO():userdao

    companion object{
        private var Instant:UserDataBase?=null
        fun getDataBaseInstant():UserDataBase{
            return Instant?:synchronized(this){
                val instance = databaseBuilder(
                    MyApplication.getAppContext(),
                    UserDataBase::class.java,
                    "app_database"
                ).allowMainThreadQueries()
                    .build()
                Instant=instance
                instance
            }
        }
        }

}