package com.example.kotlinproject.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinproject.R
import com.example.kotlinproject.data.ItemsRepositoryImpl
import com.example.kotlinproject.databinding.FragmentItemsBinding
import com.example.kotlinproject.databinding.FragmentOnBoardingBinding
import com.example.kotlinproject.domain.ItemsInteractor
import com.example.kotlinproject.presentation.adapter.ItemsAdapter
import com.example.kotlinproject.presentation.adapter.listener.ItemsListener
import com.example.kotlinproject.model.ItemsModel

class ItemsFragment : Fragment(), ItemsListener, ItemsView {

    private var _viewBinding: FragmentItemsBinding? = null
    private val viewBinding get() = _viewBinding!!

    private lateinit var itemsAdapter: ItemsAdapter
    lateinit var itemsPresenter: ItemsPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _viewBinding = FragmentItemsBinding.inflate(inflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemsPresenter = ItemsPresenter(this, ItemsInteractor(ItemsRepositoryImpl()))
        itemsAdapter = ItemsAdapter(this)

        viewBinding.rcView.adapter = itemsAdapter

        itemsPresenter.getData()

    }

    override fun onClick() {
       itemsPresenter.imageViewCLicked()
    }

    override fun onElementSelected(name: String, date: String, imageView: Int) {

        itemsPresenter.elementSelected(name, date, imageView)

    }

    override fun dataReceived(list: List<ItemsModel>) {
        itemsAdapter.submitList(list)
    }

    override fun imageViewCLicked(msg: Int) {
        Toast.makeText(context, getString(R.string.image_view_string), Toast.LENGTH_SHORT).show()
    }

    override fun goToDetails(name: String, date: String, imageView: Int) {
        val detailsFragment = DetalesFragment()
        val bundle = Bundle()
        bundle.putString("name", name)
        bundle.putString("date", date)
        bundle.putInt("imageVIew", imageView)
        detailsFragment.arguments = bundle

        //ADD method we will not use
        //We will use replace
        //replace always have addToBackstack to go back, or if we don't have addToBackstack we will not back
        parentFragmentManager.beginTransaction()
            .replace(R.id.activity_container, detailsFragment)
            .addToBackStack("Details")
            .commit()
    }
}