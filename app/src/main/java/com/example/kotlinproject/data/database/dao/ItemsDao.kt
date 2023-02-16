package com.example.kotlinproject.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.IGNORE
import androidx.room.Query
import com.example.kotlinproject.data.database.FavoritesEntity
import com.example.kotlinproject.data.database.ItemsEntity
import io.reactivex.Observable
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemsDao {

    @Insert
    fun insertItemsEntity(itemsEntity: ItemsEntity)

    @Query("SELECT (SELECT COUNT(*) FROM ItemsEntity) !=0")
    fun doesItemsEntityExist(): Observable<Boolean>

    @Query("SELECT * FROM ItemsEntity")
    fun getItemsEntities():Observable<List<ItemsEntity>>

    @Query("UPDATE ItemsEntity SET isFavorite =:isFavorite WHERE description =:description")
    fun addToFavorite(description: String, isFavorite: Boolean)

    @Query("DELETE FROM ItemsEntity WHERE description =:description")
    fun deleteItemEntityByDescription(description: String)

    @Query("SELECT * FROM ItemsEntity WHERE description =:searchText")
    fun findItemEntityByDescription(searchText: String): ItemsEntity

    @Insert(onConflict = IGNORE)  // ignore when conflict occurs (ignore items if same)
    fun insetFavoritesEntity(favoritesEntity: FavoritesEntity)

    @Query("SELECT * FROM FavoritesEntity")
    fun getFavoritesEntities(): List<FavoritesEntity>
}