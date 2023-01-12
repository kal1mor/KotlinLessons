package com.example.kotlinproject.domain.auth
import com.example.kotlinproject.domain.model.UserModel
import javax.inject.Inject

class AuthInteractor @Inject constructor(
    private val authRepository: AuthRepository) {

    suspend fun loginUser (userName: String, userPassword: String){
        authRepository.loginUser(userName,userPassword)
    }

    suspend fun getUserCreds(): UserModel{
        return authRepository.showUserCreds()
    }

    suspend fun cheackUserExist(): Boolean {
        return authRepository.doesUserExist()
    }

    suspend fun userLogout(){
        return authRepository.userLogout()
    }
}