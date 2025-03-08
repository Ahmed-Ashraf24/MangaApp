package com.example.mangaapp.presentaion.Screens

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.mangaapp.Domain.Entity.Chapter
import com.example.mangaapp.R
import com.example.mangaapp.databinding.ActivityPanelBinding
import com.example.mangaapp.presentaion.ViewModels.MangaViewModel

class PanelActivity : AppCompatActivity() {
    lateinit var binding: ActivityPanelBinding
    val panelsURL=ArrayList<String>()
    val mangaViewModel=MangaViewModel()
    private var panelIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_panel)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mangaPanel)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding=ActivityPanelBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d("Chapter ID",intent.getStringExtra("Chapter Id")!!)

        binding.btnNext.setOnClickListener {
            Log.d("next button check",(panelIndex < panelsURL.size - 1).toString())
            if (panelIndex < panelsURL.size - 1) {
                Log.d("chapter panels url from button: ",panelsURL[panelIndex])

                Glide.with(this).load(panelsURL[panelIndex])
                    .into(binding.mangaPanel)
                panelIndex++
                binding.btnPrev.isEnabled = true
            }
        }

        binding.btnPrev.setOnClickListener {
            if (panelIndex > 0) {
                panelIndex--
                Glide.with(this).load(panelsURL[panelIndex])
                    .into(binding.mangaPanel)

                binding.btnPrev.isEnabled = true
            }
            else{
                Log.d("error here is the array of urls from the button:",panelsURL.toString())
            }
        }
        val chapter=Chapter(chapterId = intent.getStringExtra("Chapter Id")!!,
            chapterName = intent.getStringExtra("Chapter Name")!!)
        mangaViewModel.fetchChapterPanels(chapter)
        mangaViewModel.chapterPanelURLList.observe(this){panels->
            panelsURL.addAll(panels.chapterPanelsURL)
            if(panelsURL.isNotEmpty()){
                Glide.with(this).load(panelsURL[panelIndex])
                    .into(binding.mangaPanel)
                panelIndex++
            }
            Log.d("chapter panels url: ",panels.chapterPanelsURL.toString())
        }




    }
}