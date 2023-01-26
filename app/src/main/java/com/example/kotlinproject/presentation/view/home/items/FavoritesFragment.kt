package com.example.kotlinproject.presentation.view.home.items

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinproject.R
import com.example.kotlinproject.databinding.FragmentFavoritesBinding
import com.example.kotlinproject.presentation.view.home.items.adapter.FavoritesAdapter
import com.example.kotlinproject.utils.NavHelper.replaceGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment() {

    private var _viewBinding: FragmentFavoritesBinding? = null
    private val viewBinding: FragmentFavoritesBinding get() = _viewBinding!!

    private lateinit var favAdapter: FavoritesAdapter
    private val viewModel: FavoritesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = FragmentFavoritesBinding.inflate(inflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        favAdapter = FavoritesAdapter()
        viewBinding.rcView.layoutManager = LinearLayoutManager(requireContext())
        viewBinding.rcView.adapter = favAdapter
        viewModel.getFavorites()

        viewModel.favorites.observe(viewLifecycleOwner){
            favAdapter.submitList(it)
        }
    }
}