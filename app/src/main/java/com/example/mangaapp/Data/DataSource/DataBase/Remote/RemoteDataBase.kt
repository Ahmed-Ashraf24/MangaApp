package com.example.mangaapp.Data.DataSource.DataBase.Remote

import android.util.Log
import androidx.credentials.Credential
import com.example.mangaapp.Data.DataSource.DataBase.DataBaseClient
import com.example.mangaapp.Data.Models.DataBaseModel.RemoteUser
import com.example.mangaapp.Data.Models.DataBaseModel.UserEntity
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.security.AuthProvider

class RemoteDataBase : DataBaseClient {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val dataBase = Firebase.firestore
    private var globalUser: UserEntity? = null
    override fun saveUser(user: UserEntity) {
        auth.createUserWithEmailAndPassword(user.userEmail, user.userPassword)
            .addOnCompleteListener { task ->
                Log.d("Task value", task.isSuccessful.toString())
                if (task.isSuccessful) {
                    val remoteUser = hashMapOf(
                        "name" to user.userName,
                        "email" to user.userEmail,
                        "age" to user.userAge,
                        "favManga" to emptyList<String>(), "histManga" to emptyList<String>()
                    )
                    dataBase.collection("Users")
                        .document(auth.currentUser!!.uid)
                        .set(remoteUser).addOnSuccessListener {
                            Log.d(
                                "saving data (firebase)",
                                "Data saved successfully"
                            )
                        }
                } else {
                    Log.d(
                        "Error occurred while logging the user in (firebase)",
                        task.exception.toString()
                    )
                }
            }
    }

    override suspend fun getUser(email: String, password: String): UserEntity? {
        return try {
            auth.signInWithEmailAndPassword(email, password).await()

            val document = dataBase.collection("Users")
                .document(auth.currentUser!!.uid)
                .get()
                .await()
            Log.d("Firestore Data", document.data.toString())


            if (document.exists()) {
                val user = document.toObject(RemoteUser::class.java)
                val favMangaList = document.get("favManga") as? List<HashMap<String, String>>
                val histMangaList = document.get("histManga") as? List<HashMap<String, String>>

                val fixedFavManga = favMangaList?.mapNotNull { it["manga Id"] } ?: emptyList()
                val histFavManga = histMangaList?.mapNotNull { it["manga Id"] } ?: emptyList()

                Log.d("User From Remote Database", user.toString())


                Log.d("User From Remote Database", user.toString())

                globalUser = UserEntity(
                    userName = document.get("name") as String,
                    userEmail = email,
                    userPassword = password,
                    userAge = document.getLong("age")?.toInt() ?: 0,
                    favManga = fixedFavManga, histManga = histFavManga
                )

                return globalUser

            } else {
                null
            }
        } catch (e: Exception) {
            Log.d(" error message : ", e.message.toString())

            null
        }
    }

    override suspend fun addMangaToFav(mangaId: String) {
        val favManga = hashMapOf(
            "manga Id" to mangaId
        )
        dataBase.collection("Users")
            .document(auth.currentUser!!.uid)
            .update("favManga", FieldValue.arrayUnion(favManga))
    }

    override suspend fun addToHistory(mangaId: String) {
        val histManga = hashMapOf(
            "manga Id" to mangaId
        )
        dataBase.collection("Users")
            .document(auth.currentUser!!.uid)
            .update("histManga", FieldValue.arrayUnion(histManga))
    }

    override fun logout() {
        auth.signOut()
    }

    override suspend fun changeUserEmail(email: String) {
        val user = auth.currentUser!!
              user.verifyBeforeUpdateEmail(email)
        }


    override suspend fun changUserPassword(password: String) {
        val user=auth.currentUser!!
        user.updatePassword(password)
    }

}