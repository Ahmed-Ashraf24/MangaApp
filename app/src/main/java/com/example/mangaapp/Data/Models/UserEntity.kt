package com.example.mangaapp.Data.Models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mangaapp.Domain.Entity.UserName

@Entity(tableName = "Users_Table")
data class UserEntity(

    @PrimaryKey(autoGenerate = true) var id:Int=0,
    val userName : String
    ,val userEmail:String
    ,val userPassword:String
    ,val userAge:Int
)
fun toUserName(user:UserEntity):UserName{
    return UserName(user.userName)

}
