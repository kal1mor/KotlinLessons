package com.example.kotlinproject.data

import com.example.kotlinproject.data.model.ItemsResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("/nkuYRM")
    suspend fun getData(): Response<ItemsResponse>
}