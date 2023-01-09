package com.example.kotlinproject.presentation.view.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinproject.domain.auth.AuthInteractor
import com.example.kotlinproject.domain.model.UserModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val authInteractor: AuthInteractor
): ViewModel() {

    private val _userCreds = MutableLiveData<UserModel>()
    val userCreds: LiveData<UserModel> = _userCreds

    fun showUserCreds(){
        viewModelScope.launch {
            _userCreds.value = authInteractor.getUserCreds()
        }

    }
}