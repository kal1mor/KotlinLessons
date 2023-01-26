package com.example.kotlinproject.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("FavoritesEntity")
class FavoritesEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo("id")
    val id: Int,
    @ColumnInfo("description")
    val description: String,
    @ColumnInfo("imageUrl")
    val imageUrl: String
)