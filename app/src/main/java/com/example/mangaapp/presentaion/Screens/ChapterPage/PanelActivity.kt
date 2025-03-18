package com.example.mangaapp.presentaion.Screens.ChapterPage

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.mangaapp.Domain.Entity.Chapter
import com.example.mangaapp.R
import com.example.mangaapp.Utilities.UIAdapters.PanelAdapter
import com.example.mangaapp.databinding.ActivityPanelBinding
import com.example.mangaapp.presentaion.ViewModels.Panels.PanelViewModel

class PanelActivity : AppCompatActivity() {
    lateinit var binding: ActivityPanelBinding
    val panelsURL=ArrayList<String>()
    private val panelsViewModel= PanelViewModel()
    private var panelIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_panel)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.panel_activity)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding=ActivityPanelBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d("Chapter ID",intent.getStringExtra("Chapter Id")!!)



        val chapter=Chapter(chapterId = intent.getStringExtra("Chapter Id")!!,
            chapterName = intent.getStringExtra("Chapter Name")!!)
        panelsViewModel.fetchChapterPanels(chapter)
        panelsViewModel.chapterPanelURLList.observe(this){panels->
            panelsURL.addAll(panels.chapterPanelsURL)
            if(panelsURL.isNotEmpty()){
                binding.recyclerView.layoutManager=LinearLayoutManager(this)
                binding.recyclerView.adapter=PanelAdapter(panelsURL)

            }
            Log.d("chapter panels url: ",panels.chapterPanelsURL.toString())
        }




    }
}