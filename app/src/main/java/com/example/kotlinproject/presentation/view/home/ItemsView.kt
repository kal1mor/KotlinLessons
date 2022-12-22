package com.example.kotlinproject.presentation.view.home

import androidx.lifecycle.LiveData
import com.example.kotlinproject.domain.model.ItemsModel

interface ItemsView {

    fun itemsReceived(itemsList: List<ItemsModel>)

    fun imageViewClicked(msg: Int)

    fun itemClicked(navigationData: NavigateWithBundle)
}