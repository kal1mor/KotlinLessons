package com.example.kotlinproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.kotlinproject.BundleConstants.KEY_IMAGE_VIEW


class DetalesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detales, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val detailsName = view.findViewById<TextView>(R.id.detalesName)
        val detailsDate = view.findViewById<TextView>(R.id.detalesDate)
        val detailsImage = view.findViewById<ImageView>(R.id.detalesImage)

        val bundle = arguments

        bundle?.let { safeBundle ->
            val name = safeBundle.getString(ItemsFragment.Companion.KEY_NAME)
            val date = safeBundle.getString(ItemsFragment.Companion.KEY_DATE) //ItemsFragment.Companion - отображает от куда взята (из какого фрагмента) константа
            val image = safeBundle.getInt(KEY_IMAGE_VIEW)

            detailsName.text = name
            detailsDate.text = date
            detailsImage.setBackgroundResource(image)
        }
    }
}