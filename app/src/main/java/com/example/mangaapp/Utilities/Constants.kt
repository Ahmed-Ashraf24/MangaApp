package com.example.mangaapp.Utilities

object Constants {
    private val EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+.com".toRegex()
    fun isValidEmail(email:String ) =EMAIL_REGEX.matches(email)

}