package com.example.kotlinproject.data.auth

import com.example.kotlinproject.data.shredpreferences.SharedPreferencesHelper
import com.example.kotlinproject.domain.auth.AuthRepository
import com.example.kotlinproject.domain.model.UserModel
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val sharedPreferencesHelper: SharedPreferencesHelper
) : AuthRepository {

    override fun loginUser(userName: String, userPassword: String) {
        sharedPreferencesHelper.saveUserName(userName)
        sharedPreferencesHelper.saveUserPassword(userPassword)
    }

    override fun showUserCreds(): UserModel {
        return sharedPreferencesHelper.getUserCreds()
    }

    override fun doesUserExist(): Boolean {
        return sharedPreferencesHelper.checkUserExist()
    }

    override fun userLogout() {
       sharedPreferencesHelper.removeUser()
    }
}