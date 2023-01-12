package com.example.kotlinproject.presentation.view.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.example.kotlinproject.R
import com.example.kotlinproject.databinding.FragmentDetalesBinding
import com.example.kotlinproject.utils.BundleConstants.KEY_IMAGE_VIEW
import com.example.kotlinproject.utils.NavHelper.navigate
import com.example.kotlinproject.utils.NavHelper.replaceGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetalesFragment : Fragment() {


    private var _binding: FragmentDetalesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DetaisViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetalesBinding.inflate(inflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val detailsName = view.findViewById<TextView>(R.id.detalesName)
        val detailsDate = view.findViewById<TextView>(R.id.detalesDate)
        val detailsImage = view.findViewById<ImageView>(R.id.detalesImage)

        val bundle = arguments

        bundle?.let { safeBundle ->
            val name = safeBundle.getString(ItemsFragment.KEY_NAME)
            val date =
                safeBundle.getString(ItemsFragment.KEY_DATE) //ItemsFragment.Companion - отображает от куда взята (из какого фрагмента) константа
            val image = safeBundle.getInt(KEY_IMAGE_VIEW)

            detailsName.text = name
            detailsDate.text = date
            detailsImage.setBackgroundResource(image)
        }

        binding.btnLogout.setOnClickListener {
            viewModel.logoutUser()
        }

        viewModel.nav.observe(viewLifecycleOwner) {
            navigate(it!!)
        }

    }
}