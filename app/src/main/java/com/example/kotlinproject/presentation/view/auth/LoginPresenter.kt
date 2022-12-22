package com.example.kotlinproject.presentation.view.auth

import com.example.kotlinproject.domain.auth.AuthInteractor
import javax.inject.Inject

class LoginPresenter @Inject constructor(
    private val authInteractor: AuthInteractor
) {

    private lateinit var loginView: LoginView

    fun setView(loginFragment: LoginFragment){
        loginView = loginFragment
    }

    fun loginUser(userName: String, userPasswor: String){
        authInteractor.loginUser(userName, userPasswor)
        loginView.userLoggedIn()
    }
}