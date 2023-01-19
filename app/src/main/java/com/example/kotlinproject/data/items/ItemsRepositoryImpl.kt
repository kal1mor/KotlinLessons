package com.example.kotlinproject.data.items

import com.example.kotlinproject.R
import com.example.kotlinproject.data.ApiService
import com.example.kotlinproject.domain.items.ItemsRepository
import com.example.kotlinproject.domain.model.ItemsModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class ItemsRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : ItemsRepository {

    override suspend fun getData(): List<ItemsModel> {
        return withContext(Dispatchers.IO) {
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