package com.example.practice1bottomnav.ui.profile

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.content.SharedPreferences
import android.view.animation.AnimationUtils
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import com.example.practice1bottomnav.App
import com.example.practice1bottomnav.R
import com.example.practice1bottomnav.databinding.FragmentProfileBinding
import com.example.practice1bottomnav.data.local.Pref
import com.example.practice1bottomnav.ui.utils.loadImage
class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var builder: AlertDialog.Builder

    private val pref by lazy {
        Pref(requireContext())
    }

    private val getCommentMedia = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val selectedFileUri = result.data?.data
            pref.saveImage(selectedFileUri.toString() )
            binding.idImageProfile.loadImage(selectedFileUri.toString())
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.textInputEditText.setText(pref.getName().toString())
        binding.idButtonSave.setOnClickListener {
            pref.saveName(binding.textInputEditText.text.toString())
        }

        binding.idImageProfile.loadImage(pref.getImage())
        binding.idImageProfile.setOnClickListener{
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            getCommentMedia.launch(intent)
        }
        binding.exit.setOnLongClickListener {
            onClick()
            true
        }
    }
    fun onClick(){
        builder = AlertDialog.Builder(requireContext())

        builder.setTitle(getString(R.string.delete_str))
            .setMessage("Do you want to exit?")
            .setCancelable(true)
            .setPositiveButton("Yes"){dialogInterface,it ->
                findNavController().navigate(R.id.phoneFragment)
            }
            .setNegativeButton("No"){dialogInterface,it ->
            }
            .show()
    }
}