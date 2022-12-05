package com.example.kotlinproject.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.kotlinproject.R
import com.example.kotlinproject.databinding.FragmentDetalesBinding
import com.example.kotlinproject.databinding.FragmentItemsBinding
import com.example.kotlinproject.databinding.FragmentOnBoardingBinding


class DetalesFragment : Fragment() {

    private var _viewBinding: FragmentDetalesBinding? = null
    private val viewBinding get() = _viewBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = FragmentDetalesBinding.inflate(inflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val bundle = arguments

        bundle?.let { safeBundle ->
            val name = safeBundle.getString("name")
            val date = safeBundle.getString("date")
            val image = safeBundle.getInt("imageVIew")

            viewBinding.detalesName.text = name
            viewBinding.detalesDate.text = date
            viewBinding.detalesImage.setBackgroundResource(image)
        }
    }
}