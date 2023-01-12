package com.example.kotlinproject.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.kotlinproject.R
import com.example.kotlinproject.databinding.ActivityMainBinding
import com.example.kotlinproject.presentation.view.auth.LoginFragment
import com.example.kotlinproject.presentation.view.home.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null

    private val vieModel: MainViewModel by viewModels()

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(R.layout.activity_main)
        setContentView(_binding!!.root)

        vieModel.checkUserExist()

        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.fragmentContainerView
        ) as NavHostFragment

        navController = navHostFragment.navController

        vieModel.nav.observe(this){
            navController.setGraph(it)
        }
    }
}