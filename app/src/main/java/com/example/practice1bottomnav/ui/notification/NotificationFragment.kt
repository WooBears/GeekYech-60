package com.example.practice1bottomnav.ui.notification

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.practice1bottomnav.databinding.FragmentNotificationBinding
import com.example.practice1bottomnav.model.Cinema
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class NotificationFragment : Fragment() {

    private lateinit var binding: FragmentNotificationBinding

    private lateinit var db: FirebaseFirestore
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNotificationBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db = FirebaseFirestore.getInstance()
        binding.btnSave.setOnClickListener {
            save()
        }
    }
    private fun save() {
        val cinema = Cinema(
            name = binding.etTitle.text.toString(),
            author = binding.etDesc.text.toString()
        )
        db.collection(FirebaseAuth.getInstance().currentUser?.uid.toString())
            .document()
            .set(cinema)
            .addOnSuccessListener {
                binding.etTitle.text.clear()
                binding.etDesc.text.clear()
                Toast.makeText(requireContext(),"Data was saved!",Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(requireContext(),"Data wasn't saved!",Toast.LENGTH_SHORT).show()
            }
    }
}