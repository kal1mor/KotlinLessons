package com.example.kotlinproject.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinproject.R
import com.example.kotlinproject.presentation.listener.ItemsListener
import com.example.kotlinproject.domain.model.ItemsModel

class ItemsAdapter(
    private  val itemsListener: ItemsListener
): RecyclerView.Adapter<ItemsViewHolder>() {

    private var listItems = mutableListOf<ItemsModel>()

    fun submitList(list: List<ItemsModel>){
        this.listItems.clear()
        this.listItems = list.toMutableList()
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_fruit, parent, false)
        return ItemsViewHolder(view, itemsListener)
    }

    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        holder.bind(listItems[position])
    }

    override fun getItemCount(): Int {
        return listItems.size //or 0
    }

}