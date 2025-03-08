package com.example.mangaapp.Data.Mapper

import com.example.mangaapp.Data.Models.DataBaseModel.UserEntity
import com.example.mangaapp.Domain.Entity.User

class UserMapper {
    companion object{
        fun toUser(user: UserEntity):User?{
            return User(
                name = user.userName
                , favManga = user.favManga,
                email = user.userEmail
                ,histManga=user.histManga
            )

        }
    }

}