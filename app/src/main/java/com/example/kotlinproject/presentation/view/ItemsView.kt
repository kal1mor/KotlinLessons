package com.example.kotlinproject.presentation.view

import com.example.kotlinproject.model.ItemsModel

interface ItemsView {

    fun dataReceived(list: List<ItemsModel>)

    fun imageViewCLicked(msg: Int)

    fun goToDetails(name: String, date: String, imageView: Int)
}