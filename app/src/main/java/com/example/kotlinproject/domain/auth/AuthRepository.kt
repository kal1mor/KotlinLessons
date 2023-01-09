package com.example.kotlinproject.domain.auth

import com.example.kotlinproject.domain.model.UserModel

interface AuthRepository {

    suspend fun loginUser(userName: String, userPassword: String)

    suspend fun showUserCreds(): UserModel

    suspend fun doesUserExist(): Boolean

    suspend fun userLogout()
}