package com.example.kotlinproject.domain

import com.example.kotlinproject.presentation.model.ItemsModel

interface ItemsRepository {
    fun getData(): List<ItemsModel>
}