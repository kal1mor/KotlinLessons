package com.example.kotlinproject

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlinproject.model.ItemsModel

class ItemsViewModel : ViewModel() {

    private val _items = MutableLiveData<List<ItemsModel>>()
    val items: LiveData<List<ItemsModel>> = _items

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    private val _bundle = MutableLiveData<NavigateWithBundle>()
    val bundle: LiveData<NavigateWithBundle> = _bundle

    fun getData() {
        val listItems = listOf<ItemsModel>(
            ItemsModel(
                R.drawable.dragon_fruit,
                "Anroid",
                "28.11.2022"
            ),
            ItemsModel(
                R.drawable.mango,
                "IOS",
                "27.11.2022"
            ),
            ItemsModel(
                R.drawable.banana,
                "C#",
                "26.11.2022"
            ),
            ItemsModel(
                R.drawable.avacado,
                "JAVA",
                "25.11.2022"
            ),
            ItemsModel(
                R.drawable.orange,
                "JS",
                "24.11.2022"
            ),
            ItemsModel(
                R.drawable.dragon_fruit,
                "C++",
                "23.11.2022"
            ),
            ItemsModel(
                R.drawable.mango,
                "C",
                "22.11.2022"
            ),
            ItemsModel(
                R.drawable.banana,
                "Ruby",
                "21.11.2022"
            ),
            ItemsModel(
                R.drawable.avacado,
                "SQL",
                "20.11.2022"
            ),
            ItemsModel(
                R.drawable.orange,
                "PHP",
                "19.11.2022"
            ),
            ItemsModel(
                R.drawable.dragon_fruit,
                ".NET",
                "18.11.2022"
            ),
            ItemsModel(
                R.drawable.mango,
                "Xamarin",
                "17.11.2022"
            ),
            ItemsModel(
                R.drawable.banana,
                "Unity",
                "16.11.2022"
            ),
            ItemsModel(
                R.drawable.avacado,
                "Flutter",
                "15.11.2022"
            )
        )
        _items.value = listItems
    }

    fun imageViewClicked() {
        _message.value = "ImageView clicked"
    }

    fun elementClicked(name: String, date: String, imageView: Int) {
        _bundle.value = NavigateWithBundle(
            name = name,
            date = date,
            image = imageView
        )
    }
}

data class NavigateWithBundle(
    val image: Int,
    val name: String,
    val date: String
)