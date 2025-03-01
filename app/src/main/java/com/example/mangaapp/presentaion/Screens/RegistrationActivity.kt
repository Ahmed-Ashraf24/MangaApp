package com.example.mangaapp.presentaion.Screens

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import com.example.mangaapp.Data.Models.UserEntity
import com.example.mangaapp.R
import com.example.mangaapp.databinding.ActivityMainBinding
import com.example.mangaapp.databinding.ActivityRegistrationBinding
import com.example.mangaapp.presentaion.ViewModels.LogInViewModel
import com.example.mangaapp.presentaion.ViewModels.SignUpViewModel

class RegistrationActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegistrationBinding
    val signUpViewModel = SignUpViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registration)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.Register)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding=ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val nameEditText=binding.nameEditText
        val ageEditText=binding.ageEditText
        val emailEditText=binding.emailEditText
        val passwordEditText=binding.passwordEditText
        val confirmPasswordEditText=binding.confirmPasswordEditText
        val regiserationErrorMessage=binding.registerationErrorMessage

        binding.signUpButton.setOnClickListener {
            if(!nameEditText.text!!.isBlank()&&
                !ageEditText.text!!.isBlank()&&
                !emailEditText.text!!.isBlank()&&
                !passwordEditText.text!!.isBlank()&&
                !confirmPasswordEditText.text!!.isBlank()){

                    if(passwordEditText.text.toString()==confirmPasswordEditText.text.toString()){
                    if(signUpViewModel.register(UserEntity(
                        userName = nameEditText.text.toString(),
                        userEmail = emailEditText.text.toString(),
                        userAge = ageEditText.text.toString().toInt(),
                        userPassword = passwordEditText.text.toString())
                    )){
                        val screenIntent= Intent(this,MainScreen::class.java).apply {
                            putExtra("name",nameEditText.text.toString())
                        }
                        startActivity(screenIntent)
                    }
                    else{
                        regiserationErrorMessage.text="the email formula is wrong"
                        regiserationErrorMessage.isVisible=true
                    }
                    }

                    else{
                        regiserationErrorMessage.text="password is not matching confirm password"
                        regiserationErrorMessage.isVisible=true

                    }

            }
            else{
                regiserationErrorMessage.text="You Must Fill All The Fields"
                regiserationErrorMessage.isVisible=true
            }

        }
    }
}