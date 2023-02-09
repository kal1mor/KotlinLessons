package com.example.kotlinproject.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinproject.di.factory.ViewModelFactory
import com.example.kotlinproject.di.factory.ViewModelKey
import com.example.kotlinproject.presentation.view.MainViewModel
import com.example.kotlinproject.presentation.view.auth.LoginViewModel
import com.example.kotlinproject.presentation.view.home.HomeViewModel
import com.example.kotlinproject.presentation.view.home.items.DetaisViewModel
import com.example.kotlinproject.presentation.view.home.items.FavoritesViewModel
import com.example.kotlinproject.presentation.view.home.items.ItemsViewModel
import com.example.kotlinproject.presentation.view.home.items.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(viewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FavoritesViewModel::class)
    abstract fun bindFavoritesViewModel(viewModel: FavoritesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetaisViewModel::class)
    abstract fun bindDetailsViewModel(viewModel: DetaisViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ItemsViewModel::class)
    abstract fun bindItemsViewModel(viewModel: ItemsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun bindSearchViewModel(viewModel: SearchViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel
}