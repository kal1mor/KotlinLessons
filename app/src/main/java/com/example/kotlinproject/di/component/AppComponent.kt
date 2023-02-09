package com.example.kotlinproject.di.component

import com.example.kotlinproject.di.*
import com.example.kotlinproject.di.factory.ScreenScope
import com.example.kotlinproject.presentation.view.MainActivity
import com.example.kotlinproject.presentation.view.auth.LoginFragment
import com.example.kotlinproject.presentation.view.home.HomeFragment
import com.example.kotlinproject.presentation.view.home.items.DetalesFragment
import com.example.kotlinproject.presentation.view.home.items.FavoritesFragment
import com.example.kotlinproject.presentation.view.home.items.ItemsFragment
import com.example.kotlinproject.presentation.view.home.items.SearchFragment
import dagger.Component

@Component(
    modules = [
        AppModule::class,
        DataModule::class,
        DataBaseModule::class,
        DomainModule::class,
        ViewModelModule::class
    ]
)

@ScreenScope
interface AppComponent {
    fun inject(loginFragment: LoginFragment)
    fun inject(detalesFragment: DetalesFragment)
    fun inject(favoriteFragment: FavoritesFragment)
    fun inject(itemsFragment: ItemsFragment)
    fun inject(searchFragment: SearchFragment)
    fun inject(homeFragment: HomeFragment)
    fun inject(mainActivity: MainActivity)
}