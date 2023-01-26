package com.example.kotlinproject.data.database.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.kotlinproject.data.database.FavoritesEntity
import com.example.kotlinproject.data.database.ItemsEntity

@Database(
    entities = [ItemsEntity::class, FavoritesEntity::class],
    version = 3,
    exportSchema = false
)
abstract class ItemsDatabase : RoomDatabase() {

    abstract fun getItemsDAO(): ItemsDao

    companion object {
        private const val DB_NAME = "items_db"
        private var BD_INSTANCE: ItemsDatabase? = null

        fun getItemsDatabaseInstance(context: Context): ItemsDatabase {
            return BD_INSTANCE ?: Room
                .databaseBuilder(
                    context.applicationContext,
                    ItemsDatabase::class.java,
                    DB_NAME
                )
                .addMigrations(MIGRATION_2_TO_3)
                .build()
                .also { BD_INSTANCE = it }
        }

        val MIGRATION_2_TO_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE ItemsEntity RENAME COLUMN imageUrl2 TO imageUrl3")
            }

        }
    }
}