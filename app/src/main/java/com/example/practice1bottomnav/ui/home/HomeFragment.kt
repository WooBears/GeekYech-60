package com.example.practice1bottomnav.ui.home

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.practice1bottomnav.App
import com.example.practice1bottomnav.R
import com.example.practice1bottomnav.databinding.FragmentHomeBinding
import com.example.practice1bottomnav.ui.home.adapter.TaskAdapter
import com.example.practice1bottomnav.model.Task

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var builder: AlertDialog.Builder
    private val adapter = TaskAdapter(this::onLongClick, this::onClick)
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

        builder.setTitle(getString(R.string.delete_str))
            .setMessage(getString(R.string.do_you_want_to_delete, task.title))
            .setCancelable(true)
            .setPositiveButton("Yes"){dialogInterface,it ->
                App.db.taskDao().delete(task)
                getData()
            }
            .setNegativeButton("No"){dialogInterface,it ->
            }
            .show()
    }
    private fun onClick(task: Task)
    {
        val action = HomeFragmentDirections.actionHomeFragmentToTaskFragment(task)
        findNavController().navigate(action)
    }
    private fun getData()
    {
        val data = App.db.taskDao().getAll()
        adapter.addTasks(data)
    }
}
