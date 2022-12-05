package com.example.kotlinproject.presentation.view

import com.example.kotlinproject.R
import com.example.kotlinproject.domain.ItemsInteractor
import com.example.kotlinproject.model.ItemsModel

class ItemsPresenter(private val itemsView: ItemsView,
    private val itemsInteractor: ItemsInteractor) {

    fun getData(){
        val listItems = itemsInteractor.getData()
        itemsView.dataReceived(listItems)

    }

    fun imageViewCLicked(){
        itemsView.imageViewCLicked(R.string.image_view_string)
    }

    fun elementSelected(name: String, date: String, imageView: Int){
        itemsView.goToDetails(name,date,imageView)
    }
}