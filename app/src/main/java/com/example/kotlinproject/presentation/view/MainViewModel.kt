package com.example.kotlinproject.presentation.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinproject.R
import com.example.kotlinproject.domain.auth.AuthInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val authInteractor: AuthInteractor
): ViewModel() {

    private val _nav = MutableLiveData<Int>()
    val nav: LiveData<Int> = _nav

    fun checkUserExist(){
        viewModelScope.launch {
           val doesUserExist = authInteractor.cheackUserExist()
            _nav.value = when(doesUserExist) {
                true -> R.navigation.main_graph
                false -> R.navigation.auth_graph
            }

        }
    }
}