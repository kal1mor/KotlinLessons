package com.example.kotlinproject.data.items

import android.util.Log
import com.example.kotlinproject.data.service.ApiService
import com.example.kotlinproject.data.service.ApiServiceSecond
import com.example.kotlinproject.domain.items.ItemsRepository
import com.example.kotlinproject.domain.model.ItemsModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named


class ItemsRepositoryImpl @Inject constructor(
    @Named("FIRST") private val apiService: ApiService,
    @Named("SECOND") private val apiServiceSecond: ApiServiceSecond
) : ItemsRepository {

    override suspend fun getData(): List<ItemsModel> {
        return withContext(Dispatchers.IO) {

            val responseSecond = apiServiceSecond.getPhotoById(3)
            Log.w("second response", responseSecond.body()?.title.toString())

            val responseSecondQuery = apiServiceSecond.getPhotoByTitle("aut ipsam quos ab placeat omnis")
            Log.w("second response query", responseSecondQuery.body()!!.get(0).toString())

            val response = apiService.getData()
            response.body()?.sampleList?.let {
                it.map {
                    ItemsModel(it.description, it.imageUrl)
                }
            } ?: kotlin.run {
                emptyList()
            }
        }
    }
}