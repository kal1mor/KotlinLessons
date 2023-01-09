package com.example.kotlinproject.data.auth

import com.example.kotlinproject.data.shredpreferences.SharedPreferencesHelper
import com.example.kotlinproject.domain.auth.AuthRepository
import com.example.kotlinproject.domain.model.UserModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val sharedPreferencesHelper: SharedPreferencesHelper
) : AuthRepository {

    override suspend fun loginUser(userName: String, userPassword: String) {
        withContext(Dispatchers.IO) {
            throw Exception()
            sharedPreferencesHelper.saveUserName(userName)
            sharedPreferencesHelper.saveUserPassword(userPassword)
        }
    }

    override suspend fun showUserCreds(): UserModel {
        return withContext(Dispatchers.IO) {
            sharedPreferencesHelper.getUserCreds()
        }
    }

    override suspend fun doesUserExist(): Boolean {
        return withContext(Dispatchers.IO){
            sharedPreferencesHelper.checkUserExist()
        }

    }

    override suspend fun userLogout() {
        withContext(Dispatchers.IO) {
            sharedPreferencesHelper.removeUser()
        }
    }
}