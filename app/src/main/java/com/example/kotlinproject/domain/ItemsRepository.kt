package com.example.kotlinproject.domain

import com.example.kotlinproject.model.ItemsModel

interface ItemsRepository {

    fun getData(): List<ItemsModel>
}