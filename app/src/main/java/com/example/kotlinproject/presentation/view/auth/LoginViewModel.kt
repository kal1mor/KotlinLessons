package com.example.kotlinproject.presentation.view.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinproject.domain.auth.AuthInteractor
import dagger.Module
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val authInteractor: AuthInteractor): ViewModel() {

    private val _nav = MutableLiveData<Unit?>()
    val nav: LiveData<Unit?> get() = _nav


    fun loginUser(userName: String, userPassword: String){
        val coroutineExceptionHandler = CoroutineExceptionHandler{_, exception ->
            Log.w("exceptionHandler called", exception.toString())
        }
        viewModelScope.launch(CoroutineName("with exception") + Dispatchers.Main + coroutineExceptionHandler){
            try {
                launch {
                    authInteractor.loginUser(userName, userPassword)
                    _nav.value = Unit
                }
            }catch (e: java.lang.Exception){
                Log.w("rxceprion", "loginUser Failed")
            }
        }
    }
}