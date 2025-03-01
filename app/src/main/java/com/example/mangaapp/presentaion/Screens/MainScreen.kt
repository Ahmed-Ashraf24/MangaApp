package com.example.mangaapp.presentaion.Screens

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mangaapp.R
import com.example.mangaapp.databinding.ActivityMainScreenBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class MainScreen : AppCompatActivity() {
    lateinit var binding: ActivityMainScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_screen)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding=ActivityMainScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.afterLoginName.text=intent.getStringExtra("name")
        binding.logoutButton.setOnClickListener {
            val auth= FirebaseAuth.getInstance()
            auth.signOut()
            val intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
}