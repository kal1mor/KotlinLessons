package com.example.kotlinproject.presentation.view.home.items

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.kotlinproject.R
import com.example.kotlinproject.databinding.FragmentDetalesBinding
import com.example.kotlinproject.utils.App
import com.example.kotlinproject.utils.BaseFragment
import com.example.kotlinproject.utils.BundleConstants.KEY_IMAGE_VIEW
import com.squareup.picasso.Picasso
import javax.inject.Inject


class DetalesFragment : BaseFragment() {


    private var _binding: FragmentDetalesBinding? = null
    private val binding get() = _binding!!



    private val viewModel: DetaisViewModel by viewModels{viewModelFactory}

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

        (requireActivity().applicationContext as App).provideAppComponent().inject(this)
        val detailsName = view.findViewById<TextView>(R.id.detalesName)
        val detailsDate = view.findViewById<TextView>(R.id.detalesDate)
        val detailsImage = view.findViewById<ImageView>(R.id.detalesImage)

        val bundle = arguments

        bundle?.let { safeBundle ->
            val name = safeBundle.getString(ItemsFragment.KEY_NAME)
            val image = safeBundle.getString(KEY_IMAGE_VIEW) //ItemsFragment.Companion - отображает от куда взята (из какого фрагмента) константа

            detailsName.text = name
            Picasso.get().load(Uri.parse(image)).into(detailsImage)
        }

        binding.btnLogout.setOnClickListener {
            binding.btnLogout.isPressed = !it.isPressed
//            viewModel.logoutUser()
        }

        viewModel.nav.observe(viewLifecycleOwner) {
            if (it != null){
                val navGraph = findNavController().navInflater.inflate(it)
                navGraph.startDestination = R.id.loginFragment
                findNavController().graph = navGraph
            }
        }

    }
}