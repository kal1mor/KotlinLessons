package com.example.kotlinproject.data.items

import android.annotation.SuppressLint
import android.util.Log
import com.example.kotlinproject.data.database.FavoritesEntity
import com.example.kotlinproject.data.database.ItemsEntity
import com.example.kotlinproject.data.database.dao.ItemsDao
import com.example.kotlinproject.data.service.ApiService
import com.example.kotlinproject.data.service.ApiServiceSecond
import com.example.kotlinproject.domain.items.ItemsRepository
import com.example.kotlinproject.domain.model.FavoritesModel
import com.example.kotlinproject.domain.model.ItemsModel
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
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

    private val compositeDisposable = CompositeDisposable()

    override fun getData(): Completable {

        return itemsDao.doesItemsEntityExist()
            .subscribeOn(Schedulers.io())
            .doAfterNext {
                if (!it){
                    val response = apiService.getData()
                   val getData = response.subscribeOn(Schedulers.io())
                        .doAfterSuccess {
                            it.sampleList.map {
                                val itemsEntity =
                                    ItemsEntity(Random().nextInt(), it.description, it.imageUrl)
                                itemsDao.insertItemsEntity(itemsEntity)
                            }
                        }
                        .doOnError {
                            Log.w("error", "when making request")
                        }
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe()
                    compositeDisposable.add(getData)

                }
            }
            .doOnComplete {
                compositeDisposable.dispose()
            }
            .ignoreElements()
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun showData(): io.reactivex.Observable<List<ItemsModel>> {
        val itemsEntity = itemsDao.getItemsEntities()
        return itemsEntity.subscribeOn(Schedulers.io())
            .map {
                it.map { item ->
                    ItemsModel(item.id, item.description, item.imageUrl, item.isFavorite ?: false)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
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