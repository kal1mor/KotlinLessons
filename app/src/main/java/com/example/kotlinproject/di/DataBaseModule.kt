package com.example.kotlinproject.di

import android.content.Context
import com.example.kotlinproject.data.database.dao.ItemsDao
import com.example.kotlinproject.data.database.dao.ItemsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {


    //Creating ItemsDao, cant return ItemsDao without create ItemsDatabase
    @Provides
    fun provideItemsDao(itemsDatabase: ItemsDatabase): ItemsDao{
        return itemsDatabase.getItemsDAO()

    }


    //Creating DataBase
    @Provides
    fun itemsDataBase(context: Context): ItemsDatabase{
        return ItemsDatabase.getItemsDatabaseInstance(context)

    }
}