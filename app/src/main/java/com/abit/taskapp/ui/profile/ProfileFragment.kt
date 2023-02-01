package com.abit.taskapp.ui.profile

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.abit.taskapp.data.Pref
import com.abit.taskapp.databinding.FragmentProfileBinding
import com.abit.taskapp.utils.loadImage
import com.abit.taskapp.utils.loadImageGallery

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var pref: Pref

    private val launcher =
        registerForActivityResult<Intent, ActivityResult>(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK && result.data != null) {
                val photoUri = result.data?.data
                pref.saveImage(photoUri.toString())
                binding.ivImageProf.loadImageGallery(photoUri.toString())
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pref = Pref(requireContext())
        binding.etName.setText(pref.getName())
        binding.etAge.setText(pref.getAge())
        binding.ivImageProf.loadImageGallery(pref.getImage())



        binding.ivImageProf.setOnClickListener {
            val intentImage = Intent()
            intentImage.action = Intent.ACTION_GET_CONTENT
            intentImage.setType("image/*")
            launcher.launch(intentImage)
        }

        binding.btnSaveProfile.setOnClickListener {
            binding.ivImageProf.loadImageGallery(pref.getImage())
            pref.saveName(binding.etName.text.toString())
            pref.setAge(binding.etAge.text.toString())
        }
    }
}