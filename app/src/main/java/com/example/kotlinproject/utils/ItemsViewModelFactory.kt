package com.example.kotlinproject.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinproject.domain.items.ItemsInteractor
import com.example.kotlinproject.presentation.ItemsViewModel

class ItemsViewModelFactory(
    private val itemsInteractor: ItemsInteractor
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ItemsViewModel(itemsInteractor) as T
    }
}