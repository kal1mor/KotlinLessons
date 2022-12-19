package com.example.kotlinproject.domain.items

import com.example.kotlinproject.domain.model.ItemsModel
import javax.inject.Inject


class ItemsInteractor @Inject constructor(
    private val itemsRepository: ItemsRepository
) {

    fun getData(): List<ItemsModel>{
        return itemsRepository.getData()
    }
}