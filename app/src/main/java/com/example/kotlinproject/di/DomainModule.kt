package com.example.kotlinproject.di

import com.example.kotlinproject.domain.auth.AuthInteractor
import com.example.kotlinproject.domain.auth.AuthRepository
import com.example.kotlinproject.domain.items.ItemsInteractor
import com.example.kotlinproject.domain.items.ItemsRepository
import dagger.Module
import dagger.Provides


@Module
class DomainModule {

    @Provides
    fun provideItemsInteractor(
        itemsRepository: ItemsRepository
    ): ItemsInteractor {
        return ItemsInteractor(itemsRepository)
    }

    @Provides
    fun provideAuthInteractor(
        authRepository: AuthRepository
    ): AuthInteractor {
        return AuthInteractor(authRepository)
    }
}