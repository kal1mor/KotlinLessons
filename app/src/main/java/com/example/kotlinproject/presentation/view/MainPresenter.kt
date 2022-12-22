package com.example.kotlinproject.presentation.view

import com.example.kotlinproject.domain.auth.AuthInteractor
import javax.inject.Inject

class MainPresenter @Inject constructor(
    private val authInteractor: AuthInteractor) {

    private lateinit var mainView: MainView

    fun setView(mainActivity: MainActivity){
        mainView = mainActivity
    }


    fun checkUserExist(){
        val doesUserExist = authInteractor.cheackUserExist()
        mainView.userExistsResult(doesUserExist)
    }
}