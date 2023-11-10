package com.example.practice1bottomnav.ui.home.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.practice1bottomnav.R
import com.example.practice1bottomnav.databinding.ItemTaskBinding
import com.example.practice1bottomnav.model.Task
import kotlinx.coroutines.joinAll
import kotlin.reflect.KFunction1

class TaskAdapter(
    private val onLongClick: (Task) ->Unit,
    private val onClick: KFunction1<Task, Unit>
) : Adapter<TaskAdapter.TaskViewHolder>() {

    private val list = arrayListOf<Task>()
    fun addTasks(tasks: List<Task>){
        list.clear()
        list.addAll(tasks)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskAdapter.TaskViewHolder {
        return TaskViewHolder(
            ItemTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(list[position])
    }
    override fun getItemCount(): Int {
        return list.size
    }
    inner class TaskViewHolder(private val binding: ItemTaskBinding) : ViewHolder(binding.root)
    {
        fun bind(task: Task){
            itemView.setBackgroundColor(if (adapterPosition % 2 == 0) Color.BLACK else Color.WHITE)
            binding.tvTitle.setTextColor(if (adapterPosition % 2 == 0) Color.WHITE else Color.BLACK)
            binding.tvDesc.setTextColor(if (adapterPosition % 2 == 0) Color.WHITE else Color.BLACK)

            binding.tvTitle.text = task.title
            binding.tvDesc.text = task.desc

            binding.root.setOnLongClickListener {
                onLongClick(task)
                true
            }
            binding.root.setOnClickListener {
                onClick(task)
            }
        }
    }
}