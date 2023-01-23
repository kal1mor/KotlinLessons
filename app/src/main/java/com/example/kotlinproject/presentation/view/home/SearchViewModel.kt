package com.example.kotlinproject.presentation.view.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.kotlinproject.domain.items.ItemsInteractor
import com.example.kotlinproject.domain.model.ItemsModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(
    private val itemsInteractor: ItemsInteractor
): ViewModel() {

    private val _item = MutableLiveData<ItemsModel>()
    val item: LiveData<ItemsModel> = _item

    fun fintItem(searchText: String){
        viewModelScope.launch {
            try {
                _item.value = itemsInteractor.findItemByDescription(searchText)
            }catch (e: Exception){
                Log.w("exception", "not item search")
            }
        }
    }
}