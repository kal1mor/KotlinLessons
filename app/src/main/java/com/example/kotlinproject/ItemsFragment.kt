package com.example.kotlinproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinproject.adapter.ItemsAdapter
import com.example.kotlinproject.adapter.ItemsViewHolder
import com.example.kotlinproject.listener.ItemsListener
import com.example.kotlinproject.model.ItemsModel

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

        viewMOdel.getData()
        viewMOdel.items.observe(viewLifecycleOwner){ listItems ->
            itemsAdapter.submitList(listItems)
        }
        viewMOdel.message.observe(viewLifecycleOwner) { msg ->
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
        }
        viewMOdel.bundle.observe(viewLifecycleOwner){ navBundle ->
            val detailsFragment = DetalesFragment()
            val bundle = Bundle()
            bundle.putString("name", navBundle.name)
            bundle.putString("date", navBundle.date)
            bundle.putInt("imageVIew", navBundle.image)
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

    override fun onClick() {
        viewMOdel.imageViewClicked()
    }

    override fun onElementSelected(name: String, date: String, imageView: Int) {
        viewMOdel.elementClicked(name, date, imageView)
    }
}