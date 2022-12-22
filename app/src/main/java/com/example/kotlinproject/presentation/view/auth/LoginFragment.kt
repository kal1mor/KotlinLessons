package com.example.kotlinproject.presentation.view.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.kotlinproject.R
import com.example.kotlinproject.databinding.FragmentLoginBinding
import com.example.kotlinproject.presentation.view.home.HomeFragment
import com.example.kotlinproject.utils.NavigationFragment.fmReplace
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class LoginFragment : Fragment(), LoginView {

    private var _binding : FragmentLoginBinding? = null
    private val binding: FragmentLoginBinding get() = _binding!!


    @Inject
    lateinit var loginPresenter: LoginPresenter

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

        loginPresenter.setView(this)

        binding.btnLogin.setOnClickListener {
            loginPresenter.loginUser(
                binding.etLogin.text.toString(),
                binding.etPassword.text.toString()
            )
        }






    }

    override fun userLoggedIn() {
        fmReplace(parentFragmentManager,HomeFragment(), false)
    }


}