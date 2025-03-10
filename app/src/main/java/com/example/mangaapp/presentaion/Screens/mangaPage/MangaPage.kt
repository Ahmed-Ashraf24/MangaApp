package com.example.mangaapp.presentaion.Screens.mangaPage

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.mangaapp.Domain.Entity.Manga
import com.example.mangaapp.R
import com.example.mangaapp.Utilities.UIAdapters.ChapterAdapter
import com.example.mangaapp.databinding.ActivityMangaPageBinding
import com.example.mangaapp.presentaion.Screens.ChapterPage.PanelActivity
import com.example.mangaapp.presentaion.ViewModels.MangaAndChaptersViewModel.MangaViewModel

class MangaPage : AppCompatActivity() {
    lateinit var binding: ActivityMangaPageBinding
    val mangaViewModel= MangaViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manga_page)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.manga_page)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding=ActivityMangaPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val mangaId=intent.getStringExtra("Manga Id")!!
        val mangaName=intent.getStringExtra("Manga Name")!!
        binding.pageTitle.text=mangaName
        val mangaGenre=intent.getStringExtra("Manga genres")!!
        binding.mangaGenres.text=mangaGenre
        val mangaDesc=intent.getStringExtra("Manga Description")!!
        binding.mangaDescription.text=mangaDesc
        val imageURL=intent.getStringExtra("Manga Image")!!
        binding.buttonFavorite.setOnClickListener {

            mangaViewModel.addMangaToFavList(Manga(id = mangaId,
                name = mangaName,
                description = mangaDesc
                , genres = mangaGenre,
                imageUrl = imageURL))
            mangaViewModel.updateFavMangaList(Manga(id = mangaId,
                name = mangaName,
                description = mangaDesc
                , genres = mangaGenre,
                imageUrl = imageURL))
        }
        Glide.with(this).load(imageURL)
            .into(binding.mangaCover)
        binding.chapterList.layoutManager=LinearLayoutManager(this)
        mangaViewModel.fetchMangaChapters(mangaId!!)
        mangaViewModel.isLoading.observe(this){isLoading->
            if (isLoading) {
                binding.blurOverlay.visibility= View.VISIBLE
                binding.progressSpinner.visibility= View.VISIBLE

            }
            else{

                binding.blurOverlay.visibility= View.GONE
                binding.progressSpinner.visibility= View.GONE
            }
        }
        binding.continueButton.setOnClickListener {
            mangaViewModel.fetchMoreMangaChapters(mangaId)
        }

        mangaViewModel.chapterList.observe(this){ chapterList->
            Log.d("Chapter data",chapterList.toString())
            binding.chapterList.layoutManager= GridLayoutManager(this, 4)
            binding.chapterList.adapter= ChapterAdapter(chapterList){ chapter ->
                val intent= Intent(this, PanelActivity::class.java)
                    .apply {
                        putExtra("Chapter Name",chapter.chapterName)
                        putExtra("Chapter Id" ,chapter.chapterId)
                    }
                startActivity(intent)

            }
            binding.chapterList.adapter!!.notifyDataSetChanged()


        }
        mangaViewModel.isChaptersCompleted.observe(this){isChaptersCompleted->
            if(isChaptersCompleted){
                binding.continueButton.isEnabled=false
                binding.continueButton.visibility=View.GONE
            }

        }

    }
}