package com.example.kotlinproject.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlinproject.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.activity_container, OnBoardingFragment())
        fragmentTransaction.commit()
    }
}