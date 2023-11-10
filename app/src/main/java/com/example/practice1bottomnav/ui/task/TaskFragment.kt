package com.example.practice1bottomnav.ui.task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.practice1bottomnav.App
import com.example.practice1bottomnav.R
import com.example.practice1bottomnav.databinding.FragmentTaskBinding
import com.example.practice1bottomnav.model.Task
import com.example.practice1bottomnav.ui.home.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class TaskFragment : Fragment() {

    private lateinit var binding: FragmentTaskBinding
    private val args by navArgs<TaskFragmentArgs>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTaskBinding.inflate(inflater,container,false)
        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSave.setOnClickListener {
            save()
        }
        binding.idUpdateBtn.setOnClickListener {
            update()
        }
    }
    private fun save()
    {
        val data = Task(
            title = binding.etTitle.text.toString(),
            desc = binding.etDesc.text.toString()
        )
        App.db.taskDao().insert(data)
        navigateUp()
    }
    private fun update()
    {
        args.currentTask.title = binding.etTitle.text.toString()
        args.currentTask.desc = binding.etDesc.text.toString()
        App.db.taskDao().update(args.currentTask)
        navigateUp()
    }
    private fun navigateUp()
    {
        findNavController().navigateUp()
    }
}