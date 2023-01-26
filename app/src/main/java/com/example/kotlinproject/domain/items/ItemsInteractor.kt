package com.example.kotlinproject.domain.items

import com.example.kotlinproject.domain.model.FavoritesModel
import com.example.kotlinproject.domain.model.ItemsModel
import javax.inject.Inject


class ItemsInteractor @Inject constructor(
    private val itemsRepository: ItemsRepository
) {

    suspend fun getData(){
        itemsRepository.getData()
    }

    suspend fun showData(): List<ItemsModel>{
        return itemsRepository.showData()
    }

    suspend fun deleteItemByDescription(description: String){
        itemsRepository.deleteItemByDescription(description)
    }

    suspend fun findItemByDescription(searchText: String): ItemsModel{
        return itemsRepository.findItemByDescription(searchText)
    }

    suspend fun onFavClicked(description: String){
        val foundItem = findItemByDescription(description)
        itemsRepository.favClicked(foundItem)
    }

    suspend fun getFavorites(): List<FavoritesModel>{
        return itemsRepository.getFavorites()
    }
}