package com.example.mangaapp.presentaion.Screens

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.lifecycle.viewModelScope
import com.example.mangaapp.R
import com.example.mangaapp.databinding.ActivityMainBinding
import com.example.mangaapp.presentaion.ViewModels.LogInViewModel

class MainActivity : AppCompatActivity() {
    val loginViewModel = LogInViewModel()

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.SignUpTextview.setOnClickListener {
            val intent= Intent(this,RegistrationActivity::class.java)
            startActivity(intent)
        }
        binding.loginButton.setOnClickListener {
            val email=binding.emailEditText
            val password=binding.passwordEditText
            val loginErrorMessage=binding.loginErrorMessage
            if(!email.text!!.isBlank()&&!password.text!!.isBlank()){
              if(loginViewModel.login(email.text.toString(),password.text.toString())!="Invalid Email"){
                  if(loginViewModel.login(email.text.toString(),password.text.toString())!=null){
                      val intent=Intent(this,MainScreen::class.java).apply {
                         putExtra("name",loginViewModel.login(email.text.toString(),password.text.toString()))
                      }
                      startActivity(intent)
                  }
                  else{
                      loginErrorMessage.text="Something Wrong In Email Or Password"
                      loginErrorMessage.isVisible=true


                  }
              }
                else{
                  loginErrorMessage.text="The Email Formula Is Wrong"
                  loginErrorMessage.isVisible=true
              }
            }
            else{
                loginErrorMessage.text="You Must Fill All The Fields"
                loginErrorMessage.isVisible=true

            }
        }
    }

}