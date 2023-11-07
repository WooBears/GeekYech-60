package com.example.practice1bottomnav.ui.authantication.code

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.practice1bottomnav.R
import com.example.practice1bottomnav.databinding.FragmentCodeBinding
import com.example.practice1bottomnav.databinding.FragmentHomeBinding
import com.example.practice1bottomnav.ui.authantication.phone.PhoneFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider

class CodeFragment : Fragment() {

    private lateinit var binding: FragmentCodeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCodeBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val verId = arguments?.getString(PhoneFragment.ID_KEY)
        binding.btnAccept.setOnClickListener {
            val credential =
                PhoneAuthProvider.getCredential(verId!!,binding.etCode.text.toString())
            signInWithPhoneAuthCredential(credential)
        }
    }

    fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        FirebaseAuth.getInstance().signInWithCredential(credential)
            .addOnSuccessListener {
                Toast.makeText(requireContext(), "Authantication is proceeded",Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.homeFragment)
            }
            .addOnFailureListener {
                Toast.makeText(requireContext(), "Authantication is failed",Toast.LENGTH_SHORT).show()
            }
    }
}