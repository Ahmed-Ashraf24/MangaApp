package com.example.mangaapp.Data.Mapper

import com.example.mangaapp.Data.Models.UserEntity
import com.example.mangaapp.Domain.Entity.UserName

class UserNameMapper {
    companion object{
        fun toUserName( user:UserEntity):UserName?{
            return UserName(
                name = user.userName
            )

        }
    }

}