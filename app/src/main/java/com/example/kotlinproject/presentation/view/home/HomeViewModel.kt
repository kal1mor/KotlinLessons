package com.example.kotlinproject.presentation.view.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinproject.R
import com.example.kotlinproject.domain.auth.AuthInteractor
import com.example.kotlinproject.domain.model.UserModel
import kotlinx.coroutines.launch
import javax.inject.Inject


class HomeViewModel @Inject constructor(
    private val authInteractor: AuthInteractor
): ViewModel() {

    private val _userCreds = MutableLiveData<UserModel>()
    val userCreds: LiveData<UserModel> = _userCreds

    private val _userNavigate = MutableLiveData<Int?>()
    val userNavigate: LiveData<Int?> = _userNavigate

    fun showUserCreds(){
        viewModelScope.launch {
            _userCreds.value = authInteractor.getUserCreds()
        }
    }

    fun userNavigate(){
        _userNavigate.value = R.navigation.main_graph
    }
}