package com.example.practice1bottomnav.ui.onBoarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.practice1bottomnav.R
import com.example.practice1bottomnav.databinding.FragmentOnBoardingBinding
import com.example.practice1bottomnav.data.local.Pref
import com.example.practice1bottomnav.ui.onBoarding.adapter.OnBoardingAdapter
import me.relex.circleindicator.CircleIndicator3


class OnBoardingFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardingBinding
    private val adapter = OnBoardingAdapter(this::onClick)

    private val pref by lazy {
        Pref(requireContext())
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardingBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewpager.adapter = adapter

        val viewPager2 = binding.viewpager

        val circleIndicator: CircleIndicator3 = binding.indicator
        circleIndicator.setViewPager(viewPager2)
    }
    private fun onClick(){
        pref.onShowed()
        findNavController().navigateUp()
    }
}