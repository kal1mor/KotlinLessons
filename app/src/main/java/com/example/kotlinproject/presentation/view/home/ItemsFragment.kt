package com.example.kotlinproject.presentation.view.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinproject.R
import com.example.kotlinproject.domain.model.ItemsModel
import com.example.kotlinproject.utils.BundleConstants
import com.example.kotlinproject.utils.NavigationFragment
import com.example.kotlinproject.presentation.adapter.ItemsAdapter
import com.example.kotlinproject.presentation.listener.ItemsListener
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


//not use create constant like this, not beautiful
//const val KEY_NAME = "name"
@AndroidEntryPoint
class ItemsFragment : Fragment(), ItemsListener, ItemsView {

    private lateinit var itemsAdapter: ItemsAdapter

    @Inject
    lateinit var itemsPresenter: ItemsPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_items, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemsPresenter.setView(this)


        itemsAdapter = ItemsAdapter(this)
        val recyclerView = view.findViewById<RecyclerView>(R.id.rcView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = itemsAdapter

        itemsPresenter.getItems()


    }

    override fun onClick() {
        itemsPresenter.imageViewClicked()
    }

    override fun onElementSelected(name: String, date: String, imageView: Int) {
        itemsPresenter.itemClicked(name, date, imageView)
    }


    override fun itemsReceived(itemsList: List<ItemsModel>) {
        itemsAdapter.submitList(itemsList)
    }

    override fun imageViewClicked(msg: Int) {
        Toast.makeText(context, getString(msg), Toast.LENGTH_SHORT).show()
    }

    override fun itemClicked(navigationData: NavigateWithBundle) {

        val detailsFragment = DetalesFragment()
        val bundle = Bundle()
        bundle.putString(KEY_NAME, navigationData.name)
        bundle.putString(KEY_DATE, navigationData.date)
        bundle.putInt(BundleConstants.KEY_IMAGE_VIEW, navigationData.image)
        detailsFragment.arguments = bundle

        //ADD method we will not use
        //We will use replace
        //replace always have addToBackstack to go back, or if we don't have addToBackstack we will not back

        //in the end of our action

        NavigationFragment.fmReplace(parentFragmentManager, detailsFragment, true)
    }

    companion object {
        //Create constant, we can use it, bc we see where we get it
        const val KEY_NAME = "name"
        const val KEY_DATE = "date"
    }
}

