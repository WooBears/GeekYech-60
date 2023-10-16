package com.example.practice1bottomnav.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.example.practice1bottomnav.R
import com.example.practice1bottomnav.databinding.FragmentHomeBinding
import com.example.practice1bottomnav.ui.home.adapter.TaskAdapter
import com.example.practice1bottomnav.ui.model.Task
import com.example.practice1bottomnav.ui.task.TaskFragment

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val adapter = TaskAdapter()

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
        setFragmentResultListener(TaskFragment.RESULT_KEY){_,bundle ->
            val data = bundle.getSerializable(TaskFragment.TASK_KEY) as Task
            adapter.addTask(data)
        }
        binding.idFab.setOnClickListener {
            findNavController().navigate(R.id.taskFragment)
        }
    }
}