package com.example.kotlinproject.presentation.view.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinproject.R
import com.example.kotlinproject.domain.auth.AuthInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetaisViewModel @Inject constructor(
    private val authInteractor: AuthInteractor
) : ViewModel() {

    private val _nav = MutableLiveData<Int?>()
    val nav: LiveData<Int?> = _nav

    fun logoutUser() {
        viewModelScope.launch(Dispatchers.Main) {
                authInteractor.userLogout()
                _nav.value = R.navigation.auth_graph
        }
    }
}