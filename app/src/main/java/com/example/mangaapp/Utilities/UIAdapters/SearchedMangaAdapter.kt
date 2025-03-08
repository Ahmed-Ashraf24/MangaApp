package com.example.mangaapp.Utilities.UIAdapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mangaapp.Domain.Entity.Manga
import com.example.mangaapp.R

class SearchedMangaAdapter(val SearchedMangaList:ArrayList<Manga>,val onItemClicked:(Manga)->Unit):RecyclerView.Adapter<SearchedMangaAdapter.SearchedViewHolder>() {
    inner class SearchedViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        private val mangaName: TextView = itemView.findViewById(R.id.SearchedMangaNameTextView)
        private val mangaImage: ImageView = itemView.findViewById(R.id.SearchedMangaImageView)
        private val mangaGenres: TextView = itemView.findViewById(R.id.SearchedMangaGenreTextView)
        fun bind(manga: Manga) {
            Log.d("ViewHolder", "mangaNameTextView: ${itemView.findViewById<TextView>(R.id.SearchedMangaNameTextView)}")

            mangaName.text = manga.name
            mangaGenres.text=manga.genres
            Glide.with(itemView.context)
                .load(manga.imageUrl)
                .placeholder(R.drawable.placeholder_image)
                .error(R.drawable.error_image)
                .into(mangaImage)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchedViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.searched_manga_item,parent,false)
        return SearchedViewHolder(view)
    }

    override fun getItemCount(): Int =SearchedMangaList.size

    override fun onBindViewHolder(holder: SearchedViewHolder, position: Int) {
        holder.bind(SearchedMangaList[position])
        holder.itemView.setOnClickListener { onItemClicked(SearchedMangaList[position]) }
    }
}