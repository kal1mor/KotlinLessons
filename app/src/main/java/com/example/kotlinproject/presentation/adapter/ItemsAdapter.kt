package com.example.kotlinproject.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinproject.R
import com.example.kotlinproject.databinding.ItemFruitBinding
import com.example.kotlinproject.presentation.adapter.listener.ItemsListener
import com.example.kotlinproject.model.ItemsModel

class ItemsAdapter(
    private  val itemsListener: ItemsListener
): RecyclerView.Adapter<ItemsViewHolder>() {

    private var listItems = mutableListOf<ItemsModel>()

    fun submitList(list: List<ItemsModel>){
        this.listItems = list.toMutableList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder {
        val viewBinding = ItemFruitBinding.inflate(LayoutInflater.from(parent.context), parent, false)
       // val view = LayoutInflater.from(parent.context).inflate(R.layout.item_fruit, parent, false)
        return ItemsViewHolder(viewBinding, itemsListener)
    }

    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        holder.bind(listItems[position])
    }

    override fun getItemCount(): Int {
        return listItems.size //or 0
    }

}