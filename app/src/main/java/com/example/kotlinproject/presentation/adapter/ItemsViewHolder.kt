package com.example.kotlinproject.presentation.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinproject.R
import com.example.kotlinproject.databinding.ItemFruitBinding
import com.example.kotlinproject.presentation.adapter.listener.ItemsListener
import com.example.kotlinproject.model.ItemsModel

class ItemsViewHolder(
    private val viewBuinding: ItemFruitBinding,
    private  val itemsListener: ItemsListener
    ): RecyclerView.ViewHolder(viewBuinding.root) {

    fun bind(itemsModel: ItemsModel){


        viewBuinding.tvName.text = itemsModel.name
        viewBuinding.ivImage.setBackgroundResource(itemsModel.image)
        viewBuinding.tvDate.text = itemsModel.date

        viewBuinding.ivImage.setOnClickListener{
            itemsListener.onClick()
        }

        itemView.setOnClickListener{
            itemsListener.onElementSelected(
                itemsModel.name,
                itemsModel.date,
                itemsModel.image
            )
        }

    }

}