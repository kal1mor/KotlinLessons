package com.example.kotlinproject.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.kotlinproject.R
import com.example.kotlinproject.databinding.ActivityMainBinding
import com.example.kotlinproject.presentation.view.auth.LoginFragment
import com.example.kotlinproject.presentation.view.home.HomeFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlin.random.Random

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NavController.OnDestinationChangedListener {

    private lateinit var binding: ActivityMainBinding

    private val vieModel: MainViewModel by viewModels()

    private lateinit var navController: NavController
    private lateinit var navHostFragment: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(R.layout.activity_main)
        setContentView(binding.root)

        vieModel.checkUserExist()

        navHostFragment = supportFragmentManager.findFragmentById(
            R.id.fragmentContainerView
        ) as NavHostFragment

        navController = navHostFragment.navController

        vieModel.nav.observe(this) {
            navController.graph = getNavGraph()
        }

        navController.addOnDestinationChangedListener(this)

        binding.bottomNavigation.setupWithNavController(navController)

        vieModel.visibility.observe(this) {
            binding.bottomNavigation.visibility = it
        }


    }

    private fun getNavGraph(): NavGraph {
        val navGraph = navHostFragment.navController.navInflater.inflate(
            R.navigation.auth_graph
        )

        val random = (1..2).random()
        if (random == 1) {
            navGraph.startDestination = R.id.loginFragment
        } else {
            navGraph.startDestination = R.id.homeFragment
        }
        return navGraph
    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        vieModel.destinationChanged(destination)
    }

    override fun onDestroy() {
        super.onDestroy()
        navController.removeOnDestinationChangedListener(this)
    }
}