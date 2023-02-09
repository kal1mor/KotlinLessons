package com.example.kotlinproject.domain.items

import com.example.kotlinproject.domain.model.FavoritesModel
import com.example.kotlinproject.domain.model.ItemsModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class ItemsInteractor @Inject constructor(
    private val itemsRepository: ItemsRepository
) {

    suspend fun getData(){
        itemsRepository.getData()
    }

    suspend fun showData(): Flow<List<ItemsModel>>{
        return itemsRepository.showData()
    }

    suspend fun deleteItemByDescription(description: String){
        itemsRepository.deleteItemByDescription(description)
    }

    suspend fun findItemByDescription(searchText: String): ItemsModel{
        return itemsRepository.findItemByDescription(searchText)
    }

    suspend fun onFavClicked(description: String, isFavorite: Boolean){
        val foundItem = findItemByDescription(description)
        itemsRepository.favClicked(foundItem, isFavorite)
    }

    suspend fun getFavorites(): List<FavoritesModel>{
        return itemsRepository.getFavorites()
    }
}