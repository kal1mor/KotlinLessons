package com.example.kotlinproject.domain

import com.example.kotlinproject.model.ItemsModel

class ItemsInteractor(private val itemsRepository: ItemsRepository) {

    fun getData(): List<ItemsModel>{
       return itemsRepository.getData()
    }
}