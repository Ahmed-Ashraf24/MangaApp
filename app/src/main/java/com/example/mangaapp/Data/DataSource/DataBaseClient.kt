package com.example.mangaapp.Data.DataSource

import com.example.mangaapp.Data.Models.UserEntity

interface DataBaseClient{

 fun saveUser(user:UserEntity)
 fun getUser(email:String,password:String) : UserEntity?


}