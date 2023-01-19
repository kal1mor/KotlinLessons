package com.example.kotlinproject.di

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.example.kotlinproject.data.service.ApiService
import com.example.kotlinproject.data.service.ApiServiceSecond
import com.example.kotlinproject.data.auth.AuthRepositoryImpl
import com.example.kotlinproject.data.items.ItemsRepositoryImpl
import com.example.kotlinproject.data.shredpreferences.SharedPreferencesHelper
import com.example.kotlinproject.domain.auth.AuthRepository
import com.example.kotlinproject.domain.items.ItemsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindItemsRepository(
        itemsRepositoryImpl: ItemsRepositoryImpl
    ): ItemsRepository

    @Binds
    abstract fun bindAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository

    companion object{

        private const val SP_KEY = "SP_KEY"
        private const val BASE_URL ="https://api.jsonserve.com"
        private const val BASE_URL_SECOND = "https://jsonplaceholder.typicode.com"

        @Provides
        fun provideSharedPreferences(
            @ApplicationContext context: Context
        ) : SharedPreferencesHelper{
            return SharedPreferencesHelper(
                context.getSharedPreferences(SP_KEY, MODE_PRIVATE)
            )
        }

        @Named("FIRST")
        @Provides
        fun  provideApiService(@Named("FIRST")retrofit: Retrofit): ApiService {
            return retrofit.create(ApiService::class.java)
        }

        @Named("FIRST")
        @Provides
        fun provideRetrofitInstance(): Retrofit{
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        @Named("SECOND")
        @Provides
        fun  provideApiServiceSecond(@Named("SECOND")retrofit: Retrofit): ApiServiceSecond {
            return retrofit.create(ApiServiceSecond::class.java)
        }

        @Named("SECOND")
        @Provides
        fun provideRetrofitInstanceSecond(): Retrofit{
            return Retrofit.Builder()
                .baseUrl(BASE_URL_SECOND)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}