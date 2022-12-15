package com.example.kotlinproject.domain

import com.example.kotlinproject.presentation.model.ItemsModel
import javax.inject.Inject


class ItemsInteractor @Inject constructor(
    private val itemsRepository: ItemsRepository
) {

    fun getData(): List<ItemsModel>{
        return itemsRepository.getData()
    }
}