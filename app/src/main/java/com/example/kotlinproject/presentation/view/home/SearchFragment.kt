package com.example.kotlinproject.presentation.view.home

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import com.example.kotlinproject.R
import com.example.kotlinproject.databinding.FragmentHomeBinding
import com.example.kotlinproject.databinding.FragmentSearchBinding
import com.example.kotlinproject.presentation.ItemsViewModel
import com.example.kotlinproject.presentation.adapter.ItemsAdapter
import com.squareup.picasso.Picasso


class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding: FragmentSearchBinding get() = _binding!!

    private val viewModel: SearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener{

            override fun onQueryTextSubmit(newText: String?): Boolean{
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.fintItem(newText?:"")
                return false
            }
        })

        viewModel.item.observe(viewLifecycleOwner){
            binding.description.text = it.description
            Picasso.get().load(Uri.parse(it.image)).into(binding.image)
        }
    }
}