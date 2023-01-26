package com.example.kotlinproject.presentation.view.home.items.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinproject.R
import com.example.kotlinproject.domain.model.FavoritesModel

class FavoritesAdapter (): RecyclerView.Adapter<FavoritesViewHolder>() {

    private var listItems = mutableListOf<FavoritesModel>()

    fun submitList(list: List<FavoritesModel>){
        this.listItems.clear()
        this.listItems = list.toMutableList()
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_favorites, parent, false)
        return FavoritesViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        holder.bind(listItems[position])
    }

    override fun getItemCount(): Int {
        return listItems.size //or 0
    }
}