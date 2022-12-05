package com.example.kotlinproject.presentation.adapter.listener

interface ItemsListener {

    fun onClick()

    fun onElementSelected(name: String, date: String, image: Int)
}