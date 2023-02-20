package com.example.kotlinproject.presentation.view.home.items

import android.content.Context.LOCATION_SERVICE
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinproject.databinding.FragmentFavoritesBinding
import com.example.kotlinproject.presentation.view.home.items.adapter.FavoritesAdapter
import com.example.kotlinproject.utils.App
import com.example.kotlinproject.utils.BaseFragment
import javax.inject.Inject


class FavoritesFragment : BaseFragment() {

    private var _viewBinding: FragmentFavoritesBinding? = null
    private val viewBinding: FragmentFavoritesBinding get() = _viewBinding!!


    private lateinit var favAdapter: FavoritesAdapter
    private val viewModel: FavoritesViewModel by viewModels{viewModelFactory}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = FragmentFavoritesBinding.inflate(inflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity().applicationContext as App).provideAppComponent().inject(this)
        favAdapter = FavoritesAdapter()
        viewBinding.rcView.layoutManager = LinearLayoutManager(requireContext())
        viewBinding.rcView.adapter = favAdapter
        viewModel.getFavorites()

        viewModel.favorites.observe(viewLifecycleOwner){
            favAdapter.submitList(it)
        }

        var locationManager: LocationManager? = null

        locationManager = requireActivity().getSystemService(LOCATION_SERVICE) as LocationManager?
        try{
            locationManager?.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                0L,
                0.0f,
                locationListener
            )
        }catch (e: Exception){
            Log.d("myTag", "Security exception, no location available")
        }
    }

    private val locationListener = LocationListener { location ->
        Toast.makeText(
            requireContext(),
            "long: ${location.longitude.toString()} short: ${location.latitude.toString()}",
            Toast.LENGTH_SHORT
        ).show()
    }
}