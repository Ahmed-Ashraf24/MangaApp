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

class FavMangaAdapter(val favMangaList:ArrayList<Manga>, val onItemClicked:(Manga)->Unit):
    RecyclerView.Adapter<FavMangaAdapter.FavViewHolder>()  {
    inner class FavViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mangaImage: ImageView = itemView.findViewById(R.id.favmangaimage)
        val mangaName: TextView = itemView.findViewById(R.id.favmangaTitle)
        val mangaGenre: TextView = itemView.findViewById(R.id.fav_manga_genre)
        val favIcon: ImageView = itemView.findViewById(R.id.favIcon)
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.fav_manga_item,parent,false)
        return FavViewHolder(view)    }

    override fun getItemCount(): Int =favMangaList.size

    override fun onBindViewHolder(holder: FavViewHolder, position: Int) {
        holder.bind(favMangaList[position])
        holder.itemView.setOnClickListener { onItemClicked(favMangaList[position]) }    }
}