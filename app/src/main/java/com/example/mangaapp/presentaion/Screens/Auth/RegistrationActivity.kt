package com.example.mangaapp.presentaion.Screens.Auth

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import com.example.mangaapp.Data.Models.DataBaseModel.UserEntity
import com.example.mangaapp.Domain.Entity.User
import com.example.mangaapp.R
import com.example.mangaapp.databinding.ActivityRegistrationBinding
import com.example.mangaapp.presentaion.Screens.MainScreen.MainScreenActivity
import com.example.mangaapp.presentaion.ViewModels.Auth.SignUpViewModel

class RegistrationActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegistrationBinding
    val signUpViewModel = SignUpViewModel()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registration)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.Register)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val nameEditText = binding.etUsername
        val ageEditText = binding.age
        val emailEditText = binding.etEmail
        val passwordEditText = binding.etPassword
        val confirmPasswordEditText = binding.confirmpassword
        val regiserationErrorMessage = binding.tvregisterError

        binding.btnRegister.setOnClickListener {
            if (!nameEditText.text!!.isBlank() &&
                !ageEditText.text!!.isBlank() &&
                !emailEditText.text!!.isBlank() &&
                !passwordEditText.text!!.isBlank() &&
                !confirmPasswordEditText.text!!.isBlank()
            ) {

                if (passwordEditText.text.toString() == confirmPasswordEditText.text.toString()) {
                    if (signUpViewModel.register(
                            UserEntity(
                                userName = nameEditText.text.toString(),
                                userEmail = emailEditText.text.toString(),
                                userAge = ageEditText.text.toString().toInt(),
                                userPassword = passwordEditText.text.toString(),
                                favManga = emptyList(),
                                histManga = emptyList()
                            )
                        )
                    ) {
                        val screenIntent = Intent(this, MainScreenActivity::class.java).apply {
                            putExtra("User", User(name = nameEditText.text.toString()!!, email = emailEditText.text.toString(), favManga = emptyList(), histManga = emptyList()))
                        }
                        startActivity(screenIntent)
                    } else {
                        regiserationErrorMessage.text = "the email formula is wrong"
                        regiserationErrorMessage.isVisible = true
                    }
                } else {
                    regiserationErrorMessage.text = "password is not matching confirm password"
                    regiserationErrorMessage.isVisible = true

                }

            } else {
                regiserationErrorMessage.text = "You Must Fill All The Fields"
                regiserationErrorMessage.isVisible = true
            }

        }
    }
}