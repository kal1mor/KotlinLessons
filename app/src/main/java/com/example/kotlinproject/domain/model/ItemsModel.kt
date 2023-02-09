package com.example.kotlinproject.domain.model

data class ItemsModel(
    val id: Int,
    val description: String,
    val image: String,
    val isFavorite: Boolean
)
