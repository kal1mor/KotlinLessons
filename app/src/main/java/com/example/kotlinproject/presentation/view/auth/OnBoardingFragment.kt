package com.example.kotlinproject.presentation.view.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.kotlinproject.databinding.FragmentOnBoardingBinding
import com.example.kotlinproject.presentation.view.home.ItemsFragment
import com.example.kotlinproject.utils.NavigationFragment.fmReplace
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class OnBoardingFragment : Fragment(), OnBoardingView {

    private var _binding: FragmentOnBoardingBinding? = null
    private val binding: FragmentOnBoardingBinding get() = _binding!!

    @Inject
    lateinit var onBoardingPresenter: OnBoardingPresenter

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

        onBoardingPresenter.setView(this)

        binding.btnFinish.setOnClickListener {
            onBoardingPresenter.goToItemsFragment()
        }


    }

    override fun goToItemsFragment() {
        fmReplace(parentFragmentManager, ItemsFragment(), false)
    }
}