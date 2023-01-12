package com.example.kotlinproject.presentation.view.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.kotlinproject.R
import com.example.kotlinproject.databinding.FragmentHomeBinding
import com.example.kotlinproject.utils.NavHelper.replaceGraph

import com.example.kotlinproject.utils.coroutins.CoroutinsExamples
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.showUserCreds()

        binding.btnGoToOnBoarding.setOnClickListener {
            viewModel.userNavigate.observe(viewLifecycleOwner) {
                replaceGraph(it!!)
            }
        }

        viewModel.userCreds.observe(viewLifecycleOwner) {
            binding.tvUserCreds.text = "${it.userName} ${it.userPassword}"
        }
    }
}