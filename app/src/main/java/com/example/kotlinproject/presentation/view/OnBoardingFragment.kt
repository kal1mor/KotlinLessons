package com.example.kotlinproject.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.kotlinproject.databinding.FragmentOnBoardingBinding
import com.example.kotlinproject.presentation.model.OnBoardingViewModel
import com.example.kotlinproject.presentation.NavigationFragment.fmReplace

class OnBoardingFragment : Fragment() {

    private val viewModel : OnBoardingViewModel by viewModels()

    private var _binding: FragmentOnBoardingBinding? = null
    private val binding: FragmentOnBoardingBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentOnBoardingBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.nav.observe(viewLifecycleOwner){
            if (it!=null) {
                fmReplace(parentFragmentManager, ItemsFragment(), false)
                viewModel.finishPerformed()
            }
        }

    }
}