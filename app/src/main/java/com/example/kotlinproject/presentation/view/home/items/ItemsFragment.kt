package com.example.kotlinproject.presentation.view.home.items

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinproject.R
import com.example.kotlinproject.utils.BundleConstants
import com.example.kotlinproject.presentation.adapter.ItemsAdapter
import com.example.kotlinproject.presentation.listener.ItemsListener
import com.example.kotlinproject.utils.NavHelper.navigateWithBundle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect


//not use create constant like this, not beautiful
//const val KEY_NAME = "name"
@AndroidEntryPoint
class ItemsFragment : Fragment(), ItemsListener {

    private lateinit var itemsAdapter: ItemsAdapter
    private val viewMOdel: ItemsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_items, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemsAdapter = ItemsAdapter(this)
        val recylerView = view.findViewById<RecyclerView>(R.id.rcView)
        recylerView.layoutManager = LinearLayoutManager(context)
        recylerView.adapter = itemsAdapter

        //Способ 1
//        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
//            viewMOdel.getData.collect()
//        }

        //Способ 2,  обертка флоу в лайф дату
//        viewMOdel.getData()
//        viewMOdel.trigger.observe(viewLifecycleOwner) {
//            viewLifecycleOwner.lifecycleScope.launchWhenResumed {
//                it.collect()
//            }
//        }

        //Способ 3, вызов suspend ункции в model
//        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
//            viewMOdel.getDataSimple()
//        }
//        viewMOdel.items.observe(viewLifecycleOwner) { listItems ->
//            itemsAdapter.submitList(listItems)
//        }

        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewMOdel.items.collect{ flowList ->
                flowList.collect{ list ->
                    itemsAdapter.submitList(list)
                }
            }
        }

        viewMOdel.message.observe(viewLifecycleOwner) { msg ->
            Toast.makeText(context, getString(msg), Toast.LENGTH_SHORT).show()
        }
        viewMOdel.bundle.observe(viewLifecycleOwner) { navBundle ->
            if (navBundle != null) {
                val bundle = Bundle()
                bundle.putString(KEY_NAME, navBundle.description)
                bundle.putString(BundleConstants.KEY_IMAGE_VIEW, navBundle.image)

                //ADD method we will not use
                //We will use replace
                //replace always have addToBackstack to go back, or if we don't have addToBackstack we will not back

                //in the end of our action
                navigateWithBundle(
                    navBundle.destinationId
                    , bundle
                )
                viewMOdel.userNavigated()
            }
        }

        viewMOdel.error.observe(viewLifecycleOwner){
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onClick() {
        viewMOdel.imageViewClicked()
    }

    override fun onElementSelected(description: String, image: String) {
        viewMOdel.elementClicked(description, image)
    }

    override fun onDeleteClicked(description: String) {
        viewMOdel.deleteItem(description)
    }

    override fun onFavClicked(description: String) {
        viewMOdel.onFavClicked(description)
    }


    companion object {
        //Create constant, we can use it, bc we see where we get it
        const val KEY_NAME = "name"
        const val KEY_DATE = "date"
    }

}