package com.example.mangaapp.presentaion.Screens.Auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.example.mangaapp.R
import com.example.mangaapp.Utilities.Constants
import com.example.mangaapp.databinding.ActivityMainBinding
import com.example.mangaapp.presentaion.Screens.MainScreen
import com.example.mangaapp.presentaion.ViewModels.Auth.LogInViewModel
import com.example.mangaapp.presentaion.ViewModels.MangaViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    val loginViewModel = LogInViewModel()
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tvGoToSignUp.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }
        binding.btnLogin.setOnClickListener {
            val email = binding.etEmailLogin.text.toString()
            val password = binding.etPasswordLogin.text.toString()
            val loginErrorMessage = binding.tvLoginError

            loginErrorMessage.isVisible = false

            if (email.isBlank() || password.isBlank()) {
                loginErrorMessage.text = "You must fill all the fields"
                loginErrorMessage.isVisible = true
                return@setOnClickListener
            }

            if (!Constants.isValidEmail(email)) {
                loginErrorMessage.text = "The email format is incorrect"
                loginErrorMessage.isVisible = true
                return@setOnClickListener
            }

            loginViewModel.login(email, password)
            loginViewModel.isLoading.observe(this) { isLoading ->
                if (isLoading) {
                    binding.blurOverlay.visibility = View.VISIBLE
                    binding.progressSpinner.visibility = View.VISIBLE
                    binding.btnLogin.isEnabled = false
                } else {
                    binding.blurOverlay.visibility = View.GONE
                    binding.progressSpinner.visibility = View.GONE
                    binding.btnLogin.isEnabled = true

                    loginViewModel.loginState.value?.let { result ->
                        result.fold(
                            onSuccess = { user ->

                                    val intent = Intent(this@MainActivity, MainScreen::class.java).apply {
                                        putExtra("User",user)
                                    }
                                    startActivity(intent)

                            },
                            onFailure = { error ->
                                binding.tvLoginError.text = error.message ?: "Login failed"
                                binding.tvLoginError.visibility = View.VISIBLE
                            }
                        )
                    }
                }
            }


        }
    }

}