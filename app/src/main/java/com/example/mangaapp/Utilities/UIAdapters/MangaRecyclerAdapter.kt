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

class MangaRecyclerAdapter(private val mangaList: ArrayList<Manga>,
                           private val onItemClick: (Manga) -> Unit
) :RecyclerView.Adapter<MangaRecyclerAdapter.MangaViewHolder>() {
    inner class MangaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val mangaName: TextView = itemView.findViewById(R.id.textViewMangaName)
        private val mangaImage: ImageView = itemView.findViewById(R.id.imageViewManga)
        private val mangaGenres: TextView = itemView.findViewById(R.id.textViewMangaGenre)

        fun bind(manga: Manga) {
            mangaName.text = manga.name
            mangaGenres.text=manga.genres
            // Load image using a library like Glide or Picasso
            Glide.with(itemView.context)
                .load(manga.imageUrl)
                .placeholder(R.drawable.placeholder_image)
                .error(R.drawable.error_image)
                .into(mangaImage)
        }
    }
    fun updateList(newList: ArrayList<Manga>) {
        mangaList.clear()
        mangaList.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MangaViewHolder {
              val view= LayoutInflater.from(parent.context).inflate(R.layout.manga_item,parent,false)
        return MangaViewHolder(view)
     }

    override fun getItemCount(): Int =mangaList.size

    override fun onBindViewHolder(holder: MangaViewHolder, position: Int) {
        holder.bind(mangaList[position])
        holder.itemView.setOnClickListener { onItemClick(mangaList[position]) }
    }
}