package com.example.kotlinproject.domain.auth
import com.example.kotlinproject.domain.model.UserModel
import javax.inject.Inject

class AuthInteractor @Inject constructor(
    private val authRepository: AuthRepository) {

    fun loginUser (userName: String, userPassword: String){
        authRepository.loginUser(userName,userPassword)
    }

    fun getUserCreds(): UserModel{
        return authRepository.showUserCreds()
    }

    fun cheackUserExist(): Boolean {
        return authRepository.doesUserExist()
    }

    fun userLogout(){
        return authRepository.userLogout()
    }
}