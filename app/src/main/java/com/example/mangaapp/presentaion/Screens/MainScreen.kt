package com.example.mangaapp.presentaion.Screens

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.mangaapp.Domain.Entity.User
import com.example.mangaapp.R
import com.example.mangaapp.databinding.ActivityMainScreenBinding
import com.example.mangaapp.presentaion.ViewModels.MangaViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch

class MainScreen : AppCompatActivity() {
    lateinit var binding: ActivityMainScreenBinding
    val mangaViewModel=MangaViewModel()
    lateinit var user:User

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_screen)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        @Suppress("DEPRECATION")
        user= intent.getParcelableExtra<User>("User")!!
        binding = ActivityMainScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, MainFragment())
                .commit()
        }
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            val selectedFragment: Fragment = when (item.itemId) {
                R.id.navigation_home -> MainFragment()
                R.id.navigation_favorites -> FavoritFragment()
                R.id.navigation_history -> HistoryFragment()
                R.id.navigation_settings -> SettingsFragment()
                else -> MainFragment()
            }
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, selectedFragment)
                .commit()
            true
        }
        binding.backButton.setOnClickListener {

            binding.editTextSearch.clearFocus()
        }
        binding.editTextSearch.setOnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                binding.backButton.visibility = View.VISIBLE
                binding.editTextSearch.apply {
                    imeOptions = EditorInfo.IME_ACTION_SEARCH
                    inputType = InputType.TYPE_CLASS_TEXT
                }
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, SearchFragment())
                    .addToBackStack(null)
                    .commit()
            } else {
                binding.editTextSearch.text.clear()
                binding.backButton.visibility = View.GONE
                supportFragmentManager.popBackStack()
            }

        }
        binding.editTextSearch.setOnEditorActionListener { v, actionId, event ->

            val searchText = binding.editTextSearch.text.toString().trim()
            val searchFragment =
                supportFragmentManager.findFragmentById(R.id.fragment_container) as? SearchFragment
            if (searchText.isNotEmpty()) {
                searchFragment!!.displayTheSearchedManga(searchText)
            }
            val inputMethodManger =
                this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManger.hideSoftInputFromWindow(v.windowToken, 0)


        }

    }

}