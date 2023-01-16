package com.example.kotlinproject.presentation.view.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlinproject.R

class OnBoardingViewModel: ViewModel() {

    private val _nav = MutableLiveData<NavigateToItems?>()
    val nav : LiveData<NavigateToItems?> = _nav

    val onBoardingText = MutableLiveData<String>("default value")

    fun finishButtonClicked(){
        _nav.value = NavigateToItems(R.id.action_onBoardingFragment_to_itemsFragment, R.id.onBoardingFragment)
    }

    fun finishPerformed(){
        _nav.value = null
    }
}

data class NavigateToItems(
    val destinationId: Int,
    val removeFragmentId: Int
)