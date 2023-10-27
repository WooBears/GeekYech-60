package com.example.practice1bottomnav.ui.home

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.example.practice1bottomnav.App
import com.example.practice1bottomnav.R
import com.example.practice1bottomnav.databinding.FragmentHomeBinding
import com.example.practice1bottomnav.ui.home.adapter.TaskAdapter
import com.example.practice1bottomnav.ui.model.Task
import com.example.practice1bottomnav.ui.task.TaskFragment

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var builder: AlertDialog.Builder
    private val adapter = TaskAdapter(this::onLongClick)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.idRecyclerView.adapter = adapter
        getData()
        binding.idFab.setOnClickListener {
            findNavController().navigate(R.id.taskFragment)
        }

    }
    private fun onLongClick(task: Task)
    {
        builder = AlertDialog.Builder(requireContext())

        builder.setTitle("Delete")
            .setMessage("Do you want to delete ${task.title}?")
            .setCancelable(true)
            .setPositiveButton("Yes"){dialogInterface,it ->
                App.db.taskDao().delete(task)
                getData()
            }
            .setNegativeButton("No"){dialogInterface,it ->

            }
            .show()
    }
    private fun getData()
    {
        val data = App.db.taskDao().getAll()
        adapter.addTasks(data)
    }
}
