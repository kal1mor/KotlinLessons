package com.example.kotlinproject.presentation.listener

interface ItemsListener {

    fun onClick()

    fun onElementSelected(name: String, date: String, image: Int)
}