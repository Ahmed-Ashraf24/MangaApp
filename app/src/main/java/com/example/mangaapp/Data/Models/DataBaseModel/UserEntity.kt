package com.example.mangaapp.Data.Models.DataBaseModel

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Users_Table")
data class UserEntity(

    @PrimaryKey(autoGenerate = true) var id:Int=0,
    val userName : String
    ,val userEmail:String
    ,val userPassword:String
    ,val userAge:Int?
    ,val favManga:List<String>
    ,val histManga:List<String>
)

