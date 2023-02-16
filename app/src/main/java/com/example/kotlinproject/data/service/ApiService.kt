package com.example.kotlinproject.data.service

import com.example.kotlinproject.data.model.ItemsResponse
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {

    @GET("/nkuYRM")
    fun getData(): Single<ItemsResponse>
}