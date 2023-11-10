package com.example.practice1bottomnav.ui.onBoarding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.practice1bottomnav.databinding.ItemOnboardBinding
import com.example.practice1bottomnav.model.OnBoarding
import com.example.practice1bottomnav.ui.utils.loadImage

class OnBoardingAdapter(
    private val onClick: () ->Unit
) : Adapter<OnBoardingAdapter.OnBoardingViewHolder>(){

    private val list = arrayListOf<OnBoarding>(
        OnBoarding("Set Up A Task", "Find the task that you need to complete", "https://cdn-icons-png.flaticon.com/512/2098/2098402.png"),
        OnBoarding("Do The Task ", "Create a logic and strategy on how to do the task", "https://cdn-icons-png.flaticon.com/512/8028/8028200.png"),
        OnBoarding("Submit The Task ", "Finally, submit the task and feel free", "https://cdn-icons-png.flaticon.com/512/1567/1567073.png")
    )
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        return OnBoardingViewHolder(
            ItemOnboardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
    override fun getItemCount(): Int {
        return list.size
    }
    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        holder.bind(list[position])
    }
    inner class OnBoardingViewHolder(private val binding: ItemOnboardBinding):ViewHolder(binding.root)
    {
        fun bind(onBoarding: OnBoarding)= with(binding)
        {
            tvTitle.text = onBoarding.title
            tvDesc.text = onBoarding.desc
            ivBoard.loadImage(onBoarding.image.toString())
            tvSkip.isVisible = adapterPosition != list.lastIndex
            btnStart.isVisible = adapterPosition == list.lastIndex

            btnStart.setOnClickListener {
                onClick()
            }
            tvSkip.setOnClickListener {
                onClick()
            }
        }
    }
}