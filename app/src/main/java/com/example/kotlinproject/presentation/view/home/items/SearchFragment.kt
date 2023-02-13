package com.example.kotlinproject.presentation.view.home.items

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.SearchView
import androidx.fragment.app.viewModels
import com.example.kotlinproject.R
import com.example.kotlinproject.databinding.FragmentSearchBinding
import com.example.kotlinproject.presentation.view.home.items.service.MusicPlayer
import com.example.kotlinproject.utils.App
import com.example.kotlinproject.utils.BaseFragment
import com.example.kotlinproject.utils.rx.RxJavaExample
import com.squareup.picasso.Picasso
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.AsyncSubject
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.ReplaySubject


class SearchFragment : BaseFragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding: FragmentSearchBinding get() = _binding!!


    private val viewModel: SearchViewModel by viewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //1 способ
        RxJavaExample().observableJust1()
        //2 способ
        RxJavaExample().observableJust2()
        //3 способ, создание Observable
        RxJavaExample().observbleJust3()
        //flatMap
        RxJavaExample().flatMap()
        //zip
        RxJavaExample().zip()
        //concat
        RxJavaExample().concat()

        RxJavaExample().asyncSubjectExample()
        RxJavaExample().publishSubjectExample()
        RxJavaExample().behaviorSubjectExample()
        RxJavaExample().replySubjectExample()

















        (requireActivity().applicationContext as App).provideAppComponent().inject(this)

//        Creating button

//        val btn = Button(context)
//        btn.background = context?.getDrawable(R.drawable.banana)
//        btn.text = context?.getText(R.string.search_fragment)
//        binding.root.addView(btn)

        AnimationUtils.loadAnimation(context, R.anim.rotate_anim).also {
            binding.start.startAnimation(it)
        }

        val animatorSet = AnimatorSet()

        val y = ObjectAnimator.ofFloat(binding.start, "scaleY", 200f, 1f)
        val x = ObjectAnimator.ofFloat(binding.start, "scaleX", 200f, 1f)

        animatorSet.playTogether(y, x)
        animatorSet.start()


        val translate = ValueAnimator.ofFloat(200f, 1f)
        translate.addUpdateListener { animation ->
            val scale = animation.animatedValue.toString().toFloat()
            binding.stop.setScaleX(scale)
            binding.stop.setScaleY(scale)
        }
        translate.repeatCount = 200
        translate.start()

        binding.start.setOnClickListener {
            requireActivity().startForegroundService(
                Intent(
                    requireContext(),
                    MusicPlayer::class.java
                )
            )
        }

        binding.stop.setOnClickListener {
            requireActivity().stopService(Intent(requireContext(), MusicPlayer::class.java))
        }

        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(newText: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.findItem(newText ?: "")
                return false
            }
        })

        viewModel.item.observe(viewLifecycleOwner) {
            binding.description.text = it.description
            Picasso.get().load(Uri.parse(it.image)).into(binding.image)
        }
    }
}