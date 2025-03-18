package com.example.mangaapp.Utilities.UIAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mangaapp.R
import com.github.chrisbanes.photoview.PhotoView

class PanelAdapter(val panelsListURL : ArrayList<String> ): RecyclerView.Adapter<PanelAdapter.PanelsViewHolder>() {
    class PanelsViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
            val image :PhotoView = itemView.findViewById( R.id.mangaPanel)
        fun binding(imageURL:String){
            Glide.with(itemView.context)
                .load(imageURL)
                .placeholder(R.drawable.placeholder_image)
                .error(R.drawable.error_image)
                .into(image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PanelsViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.panel_item,parent,false)
        return PanelsViewHolder(view)
    }

    override fun getItemCount(): Int =panelsListURL.size

    override fun onBindViewHolder(holder: PanelsViewHolder, position: Int) {
        holder.binding(panelsListURL[position])
    }
}