package com.example.kotlinproject.presentation.view.home.items.adapter

import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinproject.R
import com.example.kotlinproject.domain.model.FavoritesModel
import com.example.kotlinproject.domain.model.ItemsModel
import com.example.kotlinproject.presentation.listener.ItemsListener
import com.squareup.picasso.Picasso

class FavoritesViewHolder(
    private val view: View
): RecyclerView.ViewHolder(view) {

    fun bind(favItems: FavoritesModel){
        val name = view.findViewById<TextView>(R.id.tvName)
        val imageView = view.findViewById<ImageView>(R.id.iv_image)

        name.text = favItems.description
        Picasso.get().load(Uri.parse(favItems.image)).into(imageView)
    }
}