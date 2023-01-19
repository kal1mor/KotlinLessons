package com.example.kotlinproject.domain.items

import com.example.kotlinproject.domain.model.ItemsModel

interface ItemsRepository {
    suspend fun getData(): List<ItemsModel>
}