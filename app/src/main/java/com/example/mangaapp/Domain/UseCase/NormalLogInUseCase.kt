package com.example.mangaapp.Domain.UseCase

import com.example.mangaapp.Data.RepoImp.NormalLogIn
import com.example.mangaapp.Domain.Entity.UserName
import com.example.mangaapp.Domain.RepoInterface.LogIn
class NormalLogInUseCase(val email :String,val password:String){
 fun excute() : UserName?{
    val logIn:LogIn=NormalLogIn(email,password)
   return logIn.LoginMethod()
}
}