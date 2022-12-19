package com.example.kotlinproject.domain.items

import com.example.kotlinproject.domain.model.ItemsModel

interface ItemsRepository {
    fun getData(): List<ItemsModel>
}