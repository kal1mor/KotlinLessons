package com.example.kotlinproject.presentation.view.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinproject.R
import com.example.kotlinproject.domain.auth.AuthInteractor
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


class LoginViewModel @Inject constructor(private val authInteractor: AuthInteractor): ViewModel() {

    private val _nav = MutableLiveData<Int?>()
    val nav: LiveData<Int?> get() = _nav

    fun loginUser(userName: String, userPassword: String){
        val coroutineExceptionHandler = CoroutineExceptionHandler{_, exception ->
            Log.w("exceptionHandler called", exception.toString())
        }
        viewModelScope.launch(CoroutineName("with exception") + Dispatchers.Main + coroutineExceptionHandler){
            try {
                launch {

                    authInteractor.loginUser(userName, userPassword)
                    _nav.value = R.id.action_loginFragment_to_homeFragment
                }
            }catch (e: Exception){
                Log.w("rxceprion", "loginUser Failed")
            }
        }
    }

    fun userNavigated(){
        _nav.value = null
    }
}

