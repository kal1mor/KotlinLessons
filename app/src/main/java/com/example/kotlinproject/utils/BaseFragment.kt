package com.example.kotlinproject.utils

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

open class BaseFragment: Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
}