package com.example.kotlinproject.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinproject.R
import com.example.kotlinproject.listener.ItemsListener
import com.example.kotlinproject.model.ItemsModel

class ItemsViewHolder(
    private val view: View,
    private  val itemsListener: ItemsListener
    ): RecyclerView.ViewHolder(view) {

    fun bind(itemsModel: ItemsModel){
        val name = view.findViewById<TextView>(R.id.tvName)
        val data = view.findViewById<TextView>(R.id.tv_date)
        val imageView = view.findViewById<ImageView>(R.id.iv_image)

        name.text = itemsModel.name
        imageView.setBackgroundResource(itemsModel.image)
        data.text = itemsModel.date

        imageView.setOnClickListener{
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