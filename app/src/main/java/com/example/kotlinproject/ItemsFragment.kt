package com.example.kotlinproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinproject.adapter.ItemsAdapter
import com.example.kotlinproject.listener.ItemsListener
import com.example.kotlinproject.model.ItemsModel

class ItemsFragment : Fragment(), ItemsListener {

    private lateinit var itemsAdapter: ItemsAdapter

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

        val listItems = listOf<ItemsModel>(
            ItemsModel (R.drawable.dragon_fruit,
                "Anroid",
                "28.11.2022"),
            ItemsModel (R.drawable.mango,
            "IOS",
            "27.11.2022"),
            ItemsModel (R.drawable.banana,
                "C#",
                "26.11.2022"),
            ItemsModel (R.drawable.avacado,
                "JAVA",
                "25.11.2022"),
            ItemsModel (R.drawable.orange,
                "JS",
                "24.11.2022"),
            ItemsModel (R.drawable.dragon_fruit,
                "C++",
                "23.11.2022"),
            ItemsModel (R.drawable.mango,
                "C",
                "22.11.2022"),
            ItemsModel (R.drawable.banana,
                "Ruby",
                "21.11.2022"),
            ItemsModel (R.drawable.avacado,
                "SQL",
                "20.11.2022"),
            ItemsModel (R.drawable.orange,
                "PHP",
                "19.11.2022"),
            ItemsModel (R.drawable.dragon_fruit,
                ".NET",
                "18.11.2022"),
            ItemsModel (R.drawable.mango,
                "Xamarin",
                "17.11.2022"),
            ItemsModel (R.drawable.banana,
                "Unity",
                "16.11.2022"),
            ItemsModel (R.drawable.avacado,
                "Flutter",
                "15.11.2022"),
        )

        itemsAdapter.submitList(listItems)


    }

    override fun onClick() {
        Toast.makeText(context, "ImageView clicked", Toast.LENGTH_SHORT).show()
    }

    override fun onElementSelected(name: String, date: String, imageView: Int) {

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