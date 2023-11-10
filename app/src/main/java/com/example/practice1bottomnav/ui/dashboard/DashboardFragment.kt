package com.example.practice1bottomnav.ui.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.practice1bottomnav.databinding.FragmentDashboardBinding
import com.example.practice1bottomnav.model.Cinema
import com.example.practice1bottomnav.model.Task
import com.example.practice1bottomnav.ui.dashboard.adapter.CinemaAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class DashboardFragment : Fragment() {

    private lateinit var binding: FragmentDashboardBinding
    private lateinit var db: FirebaseFirestore
    private val adapter = CinemaAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDashboardBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerview.adapter = adapter
        db = FirebaseFirestore.getInstance()
        db.collection(FirebaseAuth.getInstance().currentUser?.uid.toString()).get()
            .addOnSuccessListener {
                val list = it.toObjects(Cinema::class.java)
                adapter.addTasks(list)
            }
            .addOnFailureListener {
                Toast.makeText(requireContext(),"Not worked", Toast.LENGTH_SHORT).show()
            }
    }
}