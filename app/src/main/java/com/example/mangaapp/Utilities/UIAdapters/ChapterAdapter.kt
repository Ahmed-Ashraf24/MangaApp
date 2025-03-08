package com.example.mangaapp.Utilities.UIAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mangaapp.Domain.Entity.Chapter
import com.example.mangaapp.R

class ChapterAdapter(private val chapterList: ArrayList<Chapter>,private val onChapterClicked:(Chapter)->Unit) :
    RecyclerView.Adapter<ChapterAdapter.ChapterViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChapterViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.chapter_item, parent, false)
        return ChapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChapterViewHolder, position: Int) {
        val title = chapterList[position].chapterName
        holder.chapterTitle.text = title
        holder.itemView.setOnClickListener { onChapterClicked(chapterList[position]) }
    }

    override fun getItemCount(): Int = chapterList.size

    inner class ChapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val chapterTitle: TextView = itemView.findViewById(R.id.chapter_name)
    }
}