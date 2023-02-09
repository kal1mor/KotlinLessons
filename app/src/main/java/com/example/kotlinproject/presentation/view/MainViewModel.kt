package com.example.kotlinproject.presentation.view

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDestination
import com.example.kotlinproject.R
import com.example.kotlinproject.domain.auth.AuthInteractor
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val authInteractor: AuthInteractor
): ViewModel() {

    private val _nav = MutableLiveData<Int>()
    val nav: LiveData<Int> = _nav

    private val _visibility = MutableLiveData<Int>()
    val visibility: LiveData<Int> = _visibility

    fun checkUserExist(){
        viewModelScope.launch {
           val doesUserExist = authInteractor.cheackUserExist()
            _nav.value = when(doesUserExist) {
                true -> R.navigation.main_graph
                false -> R.navigation.auth_graph
            }

        }
    }

    fun destinationChanged(destination: NavDestination){
        if (destination.id == R.id.loginFragment || destination.id == R.id.homeFragment){
            _visibility.value = View.GONE
        }else{
            _visibility.value = View.VISIBLE
        }
    }
}