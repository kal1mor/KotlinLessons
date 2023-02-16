package com.example.kotlinproject.presentation.view.home.items

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinproject.R
import com.example.kotlinproject.domain.items.ItemsInteractor
import com.example.kotlinproject.domain.model.ItemsModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject


class ItemsViewModel @Inject constructor(
    private val itemsInteractor: ItemsInteractor
) : ViewModel() {

    private val _items = MutableLiveData<List<ItemsModel>>()
    val items: LiveData<List<ItemsModel>> = _items
    val getData = flow { emit(itemsInteractor.getData()) }

    private val _trigger = MutableLiveData<Flow<Unit>>()
    val trigger = _trigger

    private val _message = MutableLiveData<Int>()
    val message: LiveData<Int> = _message

    private val _bundle = MutableLiveData<NavigateWithBundle?>()
    val bundle: LiveData<NavigateWithBundle?> = _bundle

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val compositeDisposable = CompositeDisposable()

    fun getData() {
        val getData = itemsInteractor.getData().subscribe({}, {
            Log.w("error get data", "get data not calling")
        })
        compositeDisposable.add(getData)

        val showData = itemsInteractor.showData().subscribe({
            _items.value = it
        }, {
            _error.value = "Error occurred while showing data"
        })
        compositeDisposable.add(showData)
    }

//        viewModelScope.launch {
//            try {
//
//                val listItems = itemsInteractor.showData()
//                listItems.collect{
//                    _items.value = it
//                }
//            }catch (e: Exception){
//                _error.value = e.message.toString()
//            }
//        }


fun imageViewClicked() {
    _message.value = R.string.image_view_clicked
}

fun elementClicked(description: String, image: String) {
    _bundle.value =
        NavigateWithBundle(
            description = description,
            image = image,
            destinationId = R.id.action_itemsFragment_to_detalesFragment
        )
}

fun userNavigated() {
    _bundle.value = null
}

fun deleteItem(description: String) {
    viewModelScope.launch {
        try {
            itemsInteractor.deleteItemByDescription(description)
        } catch (e: Exception) {
            _error.value = e.message.toString()
        }
    }
}

fun onFavClicked(description: String, isFavorite: Boolean) {
    viewModelScope.launch {
        itemsInteractor.onFavClicked(description, isFavorite)
    }
}

//suspend fun getDataSimple() {
//    itemsInteractor.getData()
//}

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}

data class NavigateWithBundle(
    val image: String,
    val description: String,
    val destinationId: Int
)



