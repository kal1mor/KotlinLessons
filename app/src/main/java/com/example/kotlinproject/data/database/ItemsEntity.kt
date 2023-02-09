package com.example.kotlinproject.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("ItemsEntity")
data class ItemsEntity(
    @PrimaryKey (autoGenerate = false)
    @ColumnInfo("id")
    val id: Int,
    @ColumnInfo("description")
    val description: String,
    @ColumnInfo("imageUrl3")
    val imageUrl: String,
    @ColumnInfo("isFavorite")
    val isFavorite: Boolean?=false
)
