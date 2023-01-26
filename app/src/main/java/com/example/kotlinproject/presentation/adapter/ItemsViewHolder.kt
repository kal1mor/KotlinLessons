package com.example.kotlinproject.presentation.adapter

import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinproject.R
import com.example.kotlinproject.presentation.listener.ItemsListener
import com.example.kotlinproject.domain.model.ItemsModel
import com.squareup.picasso.Picasso

class ItemsViewHolder(
    private val view: View,
    private  val itemsListener: ItemsListener
    ): RecyclerView.ViewHolder(view) {

    fun bind(itemsModel: ItemsModel){
        val name = view.findViewById<TextView>(R.id.tvName)
        val imageView = view.findViewById<ImageView>(R.id.iv_image)
        val deleteView = view.findViewById<ImageView>(R.id.delete)
        val favImage = view.findViewById<ImageView>(R.id.btnFav)

        name.text = itemsModel.description
        Picasso.get().load(Uri.parse(itemsModel.image)).into(imageView)

        imageView.setOnClickListener{
            itemsListener.onClick()
        }

        itemView.setOnClickListener{
            itemsListener.onElementSelected(
                itemsModel.description,
                itemsModel.image

            )
        }
        deleteView.setOnClickListener{
            itemsListener.onDeleteClicked(itemsModel.description)
        }

        favImage.setOnClickListener {
            itemsListener.onFavClicked(itemsModel.description)
        }

    }

}