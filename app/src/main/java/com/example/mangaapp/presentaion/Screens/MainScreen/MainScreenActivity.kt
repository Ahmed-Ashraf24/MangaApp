package com.example.mangaapp.presentaion.Screens.MainScreen

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.lifecycleScope
import com.example.mangaapp.Domain.Entity.Manga
import com.example.mangaapp.Domain.Entity.User
import com.example.mangaapp.R
import com.example.mangaapp.databinding.ActivityMainScreenBinding
import com.example.mangaapp.presentaion.Screens.mangaPage.MangaPageFragment
import com.example.mangaapp.presentaion.Screens.MainScreen.Favorite.FavoritFragment
import com.example.mangaapp.presentaion.Screens.MainScreen.History.HistoryFragment
import com.example.mangaapp.presentaion.Screens.MainScreen.Home.MainFragment
import com.example.mangaapp.presentaion.Screens.MainScreen.Setting.SettingsFragment
import com.example.mangaapp.presentaion.ViewModels.Manga.MangaViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

@Suppress("DEPRECATION")
class MainScreenActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainScreenBinding
    lateinit var user:User
     var selectedManga: Manga?=null
    val mangaViewModel: MangaViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_screen)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        Toast.makeText(this,"This app sources its content from the MangaDex API",Toast.LENGTH_LONG).show()

        @Suppress("DEPRECATION")
        user= intent.getParcelableExtra<User>("User")!!
        lifecycleScope.launch {
            val fetchingManga= async {  mangaViewModel.fetchMangaList()}
            fetchingManga.await()
            async {    user!!.favManga.forEach {
                mangaViewModel.getMangaFromIdForFav(it)
            }}.await()
            async {    user!!.histManga.forEach {
                mangaViewModel.getMangaFromIdForHistory(it)
            }}.await()
        }
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


    }
    override fun onBackPressed() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)

        when (currentFragment) {
            is MangaPageFragment -> {

                supportFragmentManager.popBackStack("SearchFragment", FragmentManager.POP_BACK_STACK_INCLUSIVE)
                super.onBackPressed()


            }
            is MainFragment -> {
                currentFragment.clearSearchUI()
                supportFragmentManager.popBackStack()
            }
            else -> {
                super.onBackPressed()
            }
        }
    }

}