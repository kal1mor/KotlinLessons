package com.example.kotlinproject.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinproject.ItemsIteractor
import com.example.kotlinproject.ItemsViewModel

class ItemsViewModelFactory(
    private val itemsInteractor: ItemsIteractor
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ItemsViewModel(itemsInteractor) as T
    }
}