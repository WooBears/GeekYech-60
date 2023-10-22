package com.example.practice1bottomnav.ui.profile

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.content.SharedPreferences
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.example.practice1bottomnav.databinding.FragmentProfileBinding
import com.example.practice1bottomnav.ui.data.local.Pref
import com.example.practice1bottomnav.ui.utils.loadImage


class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    private val pref by lazy {
        Pref(requireContext())
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.idEditName.setText(pref.getName().toString())
        binding.idButtonSave.setOnClickListener {
            pref.saveName(binding.idEditName.text.toString())
        }

        binding.idImageProfile.loadImage(pref.getImage().toString())
        binding.idImageProfile.setOnClickListener{
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, IMAGE_REQUEST_CODE)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_REQUEST_CODE && resultCode == Activity.RESULT_OK)
        {
            val imageURI = data?.data
            pref.saveImage(imageURI)
            binding.idImageProfile.loadImage(imageURI.toString())
        }
    }
    companion object {
        private const val IMAGE_REQUEST_CODE = 1
    }
}