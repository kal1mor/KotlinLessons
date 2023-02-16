package com.example.kotlinproject.domain.items

import android.util.Log
import com.example.kotlinproject.domain.model.FavoritesModel
import com.example.kotlinproject.domain.model.ItemsModel
import io.reactivex.Completable
import io.reactivex.Observable
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class ItemsInteractor @Inject constructor(
    private val itemsRepository: ItemsRepository
) {

     fun getData(): Completable{
         Log.w("data called?", "data called?")
        return itemsRepository.getData()
    }

     fun showData(): Observable<List<ItemsModel>>{
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