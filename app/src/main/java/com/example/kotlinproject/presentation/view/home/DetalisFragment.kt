package com.example.kotlinproject.presentation.view.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.kotlinproject.R
import com.example.kotlinproject.databinding.FragmentDetalesBinding
import com.example.kotlinproject.presentation.view.auth.LoginFragment
import com.example.kotlinproject.presentation.view.home.ItemsFragment.Companion.KEY_DATE
import com.example.kotlinproject.presentation.view.home.ItemsFragment.Companion.KEY_NAME
import com.example.kotlinproject.utils.BundleConstants.KEY_IMAGE_VIEW
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetalesFragment : Fragment(), DetailsView {


    private var _binding: FragmentDetalesBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var detailsPresenter: DetailsPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
     _binding = FragmentDetalesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detailsPresenter.setView(this)

        val bundle = arguments

        bundle?.let { safeBundle ->
            detailsPresenter.getArguments(
                safeBundle.getString(KEY_NAME),
                safeBundle.getString(KEY_DATE),
                safeBundle.getInt(KEY_IMAGE_VIEW)
            )
        }



        binding.btnLogout.setOnClickListener {
            detailsPresenter.logoutUser()
        }




    }

    override fun userLoggetOut() {
            parentFragmentManager.beginTransaction()
                .add(R.id.activity_container, LoginFragment())
                .commit()
    }


    override fun displayItemData(name: String, date: String, imageView: Int) {
        binding.detalesName.text = name + ""
        binding.detalesDate.text = date
        binding.detalesImage.setBackgroundResource(imageView)
    }
}