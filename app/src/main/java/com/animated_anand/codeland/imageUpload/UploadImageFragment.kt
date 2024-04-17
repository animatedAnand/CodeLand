package com.animated_anand.codeland.imageUpload

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.animated_anand.codeland.R
import com.animated_anand.codeland.databinding.FragmentUploadImageBinding

class UploadImageFragment : Fragment() {
    private lateinit var binding: FragmentUploadImageBinding
    private  var selectedImageUri : Uri? = null
    private  val REQUEST_IMAGE_PICK = 100
    private  val PERMISSION_REQUEST_CODE = 101

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUploadImageBinding.inflate(layoutInflater)
        uploadImage()
        viewImageFullscreen()

        return binding.root
    }

    private fun viewImageFullscreen() {
        binding.btViewImage.setOnClickListener {
            selectedImageUri?.let { uri ->
                val bundle = Bundle().apply {
                    putString("image_uri", uri.toString())
                }
                findNavController().navigate(R.id.action_uploadImageFragment_to_imageViewFragment, bundle)
            }
        }
    }

    private fun uploadImage() {
        binding.btUploadImage.setOnClickListener {
            openGallery()
        }
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, REQUEST_IMAGE_PICK)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_PICK && resultCode == Activity.RESULT_OK && data != null) {
            selectedImageUri = data.data
            binding.ivImage.setImageURI(selectedImageUri)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openGallery()
            }
        }
    }
}