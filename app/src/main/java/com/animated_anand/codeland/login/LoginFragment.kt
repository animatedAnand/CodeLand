package com.animated_anand.codeland.login

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.animated_anand.codeland.ImageActivity
import com.animated_anand.codeland.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupPhoneNumberClickListeners()
        binding.btLogin.setOnClickListener {
            val username = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (username.isEmpty()) {
                binding.etUsername.error = "Username cannot be empty"
                binding.etUsername.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                binding.etPassword.error = "Password cannot be empty"
                binding.etPassword.requestFocus()
                return@setOnClickListener
            }

            // Perform login logic here (e.g., check credentials against server)
            if (isValidCredentials(username, password)) {
                navigateToImageActivity()
            } else {
                Toast.makeText(requireContext(), "Invalid username or password", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupPhoneNumberClickListeners() {
        binding.tvContactNo1 .setOnClickListener {
            openDialer(binding.tvContactNo1.text.toString()) // Replace "1234567890" with your actual phone number
        }

        binding.tvContactNo2.setOnClickListener {
            openDialer(binding.tvContactNo2.text.toString()) // Replace "0987654321" with your actual phone number
        }
    }

    private fun openDialer(phoneNumber: String) {
        val dialIntent = Intent(Intent.ACTION_DIAL)
        dialIntent.data = Uri.parse("tel:$phoneNumber")
        startActivity(dialIntent)
    }

    private fun isValidCredentials(username: String, password: String): Boolean {
        // Example validation logic (replace with your actual validation logic)
        return true
    }

    private fun navigateToImageActivity() {
        val intent = Intent(requireContext(), ImageActivity::class.java)
        startActivity(intent)
        requireActivity().finish() // Finish the LoginActivity so the user can't go back
    }
}