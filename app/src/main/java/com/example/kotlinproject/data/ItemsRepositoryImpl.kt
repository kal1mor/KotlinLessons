package com.example.kotlinproject.data

import com.example.kotlinproject.R
import com.example.kotlinproject.domain.ItemsRepository
import com.example.kotlinproject.presentation.model.ItemsModel
import javax.inject.Inject


class ItemsRepositoryImpl @Inject constructor(): ItemsRepository {

    override fun getData(): List<ItemsModel> {

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
        return listItems
    }



}