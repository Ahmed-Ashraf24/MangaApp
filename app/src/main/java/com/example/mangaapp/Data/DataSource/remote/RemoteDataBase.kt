package com.example.mangaapp.Data.DataSource.remote

import android.util.Log
import com.example.mangaapp.Data.DataSource.DataBaseClient
import com.example.mangaapp.Data.Models.UserEntity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RemoteDataBase : DataBaseClient {
    val auth: FirebaseAuth = FirebaseAuth.getInstance()
    val dataBase = Firebase.firestore
    override fun saveUser(user: UserEntity) {
        auth.createUserWithEmailAndPassword(user.userEmail, user.userPassword)
            .addOnCompleteListener { task ->
                Log.d("Task value",task.isSuccessful.toString())
                if (task.isSuccessful) {
                    val remoteUser = hashMapOf(
                        "name" to user.userName, "email" to user.userEmail, "age" to user.userAge
                    )
                    dataBase.collection("Users")
                        .document(auth.currentUser!!.uid)
                        .set(remoteUser).addOnSuccessListener { Log.d(
                            "saving data (firebase)",
                            "Data saved successfuly"
                        ) }
                } else {
                    Log.d(
                        "Error ocuured while loging the user in (firebase)",
                        task.exception.toString()
                    )
                }
            }
    }

    override fun getUser(email: String, password: String): UserEntity? {
        var name = ""
        var age = 0
        var userExist = false
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                dataBase.collection("Users")
                    .document(auth.currentUser!!.uid).get()
                    .addOnSuccessListener { document ->
                        if (document.exists()) {
                            name = document.getString("name")!!
                            age = document.getString("age")!!.toInt()
                            userExist = true
                        } else {
                            userExist = false
                        }

                    }
            }
        }
        if (userExist) {
            return UserEntity(
                userName = name,
                userEmail = email,
                userPassword = password,
                userAge = age
            )
        } else {
            return null
        }
    }
}