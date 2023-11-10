package com.example.practice1bottomnav.ui.dashboard.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.practice1bottomnav.databinding.ItemTaskBinding
import com.example.practice1bottomnav.model.Cinema
import com.example.practice1bottomnav.model.Task

class CinemaAdapter: Adapter<CinemaAdapter.ItemViewHolder>(

) {
    private val list = arrayListOf<Cinema>()

    fun addTasks(cinema: List<Cinema>){
        list.clear()
        list.addAll(cinema)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class ItemViewHolder(private val binding: ItemTaskBinding): ViewHolder(binding.root)
    {
        fun bind(cinema: Cinema)
        {
            binding.tvTitle.text = cinema.name
            binding.tvDesc.text = cinema.author
        }
    }
}