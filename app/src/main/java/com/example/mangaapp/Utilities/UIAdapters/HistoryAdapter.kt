package com.example.mangaapp.Utilities.UIAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mangaapp.Domain.Entity.Manga
import com.example.mangaapp.R

class HistoryAdapter(val historyMangaList:ArrayList<Manga>, val onItemClicked:(Manga)->Unit):
    RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>()   {
    class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val mangaImage: ImageView = itemView.findViewById(R.id.history_manga_image)
        val mangaName: TextView = itemView.findViewById(R.id.history_manga_Title)
        val mangaGenre: TextView = itemView.findViewById(R.id.history_manga_genre)
        fun bind(manga :Manga){
            mangaName.text=manga.name
            mangaGenre.text=manga.genres
            Glide.with(itemView.context)
                .load(manga.imageUrl)
                .placeholder(R.drawable.placeholder_image)
                .error(R.drawable.error_image)
                .into(mangaImage)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.history_manga_item,parent,false)
        return HistoryViewHolder(view)    }

    override fun getItemCount(): Int = historyMangaList.size

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
holder.bind(historyMangaList[position])
    holder.itemView.setOnClickListener { onItemClicked(historyMangaList[position]) }
    }
}