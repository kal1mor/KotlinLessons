package com.example.kotlinproject.domain.items

import com.example.kotlinproject.domain.model.FavoritesModel
import com.example.kotlinproject.domain.model.ItemsModel

interface ItemsRepository {

    suspend fun getData()

    suspend fun showData(): List<ItemsModel>

    suspend fun deleteItemByDescription(description: String)

    suspend fun findItemByDescription(searchText: String): ItemsModel

    suspend fun favClicked(itemsModel: ItemsModel)

    suspend fun getFavorites(): List<FavoritesModel>
}