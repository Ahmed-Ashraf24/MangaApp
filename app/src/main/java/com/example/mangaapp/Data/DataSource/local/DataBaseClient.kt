package com.example.mangaapp.Data.DataSource.local

import com.example.mangaapp.Data.Models.UserDataBase
import com.example.mangaapp.Data.Models.UserEntity

class DataBaseClient() {
 private val instance=UserDataBase.getDataBaseInstant()
private val DAO=instance.userDAO()
 fun insertUser(user:UserEntity){
  DAO.insertUser(user)
 }
 fun getUser(email:String,password:String) : UserEntity?{
  return DAO.getUser(email,password)
 }


}