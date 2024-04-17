package com.animated_anand.codeland.imageUpload

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.animated_anand.codeland.R
import com.animated_anand.codeland.databinding.FragmentImageViewBinding

class ImageViewFragment : Fragment() {
    private lateinit var binding: FragmentImageViewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentImageViewBinding.inflate(layoutInflater)
        closeFullScreenImage()
        return binding.root
    }

    private fun closeFullScreenImage() {
        binding.ibCloseFullScreen.setOnClickListener{
            findNavController().navigate(R.id.action_imageViewFragment_to_uploadImageFragment)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Get the image URI from the arguments Bundle
        val imageUriString = arguments?.getString("image_uri")
        imageUriString?.let { uriString ->
            val imageUri = Uri.parse(uriString)

            // Set the image URI to the ImageView
            binding.imageViewFullScreen.setImageURI(imageUri)
        }
    }
}