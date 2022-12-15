package com.example.kotlinproject.presentation.view.dataBinding

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel() {

    val userEmail = MutableLiveData<String>()
    val userPassword = MutableLiveData<String>()

    val userCreds = MutableLiveData<String>()

    fun showCreds(){

        userCreds.value = "${userEmail.value.toString()} \n ${userPassword.value.toString()}"

    }

}