package com.example.kotlinproject.presentation.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinproject.domain.auth.AuthInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val authInteractor: AuthInteractor
): ViewModel() {

    private val _userExist = MutableLiveData<Boolean>()
    val userExist: LiveData<Boolean> = _userExist

    fun checkUserExist(){
        viewModelScope.launch {
            _userExist.value = authInteractor.cheackUserExist()
        }
    }
}