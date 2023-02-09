package com.example.kotlinproject.presentation.view.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinproject.databinding.FragmentLoginBinding
import com.example.kotlinproject.utils.App
import com.example.kotlinproject.utils.BaseFragment
import com.example.kotlinproject.utils.NavHelper.navigate
import javax.inject.Inject


class LoginFragment : BaseFragment() {

    private var _binding : FragmentLoginBinding? = null
    private val binding: FragmentLoginBinding get() = _binding!!

    private val viewModel : LoginViewModel by viewModels{viewModelFactory}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity().applicationContext as App).provideAppComponent().inject(this)
        binding.btnLogin.setOnClickListener {
            viewModel.loginUser(
                binding.etLogin.text.toString(),
                binding.etPassword.text.toString()
            )
        }


        viewModel.nav.observe(viewLifecycleOwner){
            if (it != null){
                navigate(it)
                viewModel.userNavigated()
            }
        }
    }
}