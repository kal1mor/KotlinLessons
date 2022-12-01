package com.example.kotlinproject.listener

interface ItemsListener {

    fun onClick()

    fun onElementSelected(name: String, date: String, image: Int)
}