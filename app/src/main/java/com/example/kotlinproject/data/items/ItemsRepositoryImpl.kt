package com.example.kotlinproject.data.items

import android.util.Log
import com.example.kotlinproject.data.database.FavoritesEntity
import com.example.kotlinproject.data.database.ItemsEntity
import com.example.kotlinproject.data.database.dao.ItemsDao
import com.example.kotlinproject.data.service.ApiService
import com.example.kotlinproject.data.service.ApiServiceSecond
import com.example.kotlinproject.domain.items.ItemsRepository
import com.example.kotlinproject.domain.model.FavoritesModel
import com.example.kotlinproject.domain.model.ItemsModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject
import javax.inject.Named


class ItemsRepositoryImpl @Inject constructor(
    @Named("FIRST") private val apiService: ApiService,
    @Named("SECOND") private val apiServiceSecond: ApiServiceSecond,
    private val itemsDao: ItemsDao
) : ItemsRepository {

    override suspend fun getData() {
        return withContext(Dispatchers.IO) {
            itemsDao.doesItemsEntityExist().collect {
                if (!it) {
                    Log.w("getData", "data not exists")
                    val response = apiService.getData()

                    Log.w("getData", response.body()?.sampleList.toString())
                    response.body()?.sampleList?.let {
                        it.map {
                            val itemsEntity =
                                ItemsEntity(Random().nextInt(), it.description, it.imageUrl)
                            itemsDao.insertItemsEntity(itemsEntity)
                        }
                    }
                }
            }
        }
    }

    override suspend fun showData(): Flow<List<ItemsModel>> {
        return withContext(Dispatchers.IO) {
            val itemsEntity = itemsDao.getItemsEntities()
            itemsEntity.map { itemsList ->
                itemsList.map { item ->
                    ItemsModel(item.id, item.description, item.imageUrl, item.isFavorite ?: false)
                }
            }
        }
    }

    override suspend fun deleteItemByDescription(description: String) {
        withContext(Dispatchers.IO) {
            itemsDao.deleteItemEntityByDescription(description)
        }
    }

    override suspend fun findItemByDescription(searchText: String): ItemsModel {
        return withContext(Dispatchers.IO) {
            val itemsEntity = itemsDao.findItemEntityByDescription(searchText)
            ItemsModel(
                itemsEntity.id,
                itemsEntity.description,
                itemsEntity.imageUrl,
                itemsEntity.isFavorite ?: false
            )
        }
    }

    override suspend fun favClicked(itemsModel: ItemsModel, isFavorite: Boolean) {
        return withContext(Dispatchers.IO) {

            itemsDao.addToFavorite(itemsModel.description, isFavorite)

            itemsDao.insetFavoritesEntity(
                FavoritesEntity(
                    itemsModel.id,
                    itemsModel.description,
                    itemsModel.image
                )
            )
        }
    }

    override suspend fun getFavorites(): List<FavoritesModel> {
        return withContext(Dispatchers.IO) {
            val favEntity = itemsDao.getFavoritesEntities()
            favEntity.map {
                FavoritesModel(it.description, it.imageUrl)
            }
        }
    }
}