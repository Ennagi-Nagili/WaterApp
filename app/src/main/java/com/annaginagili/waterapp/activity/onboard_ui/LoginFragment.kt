package com.annaginagili.waterapp.activity.onboard_ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.annaginagili.waterapp.activity.MainActivity
import com.annaginagili.waterapp.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth
import java.util.Calendar

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val auth = FirebaseAuth.getInstance()

        binding.signupRedirect.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToSignupFragment()
            findNavController().navigate(action)
        }

        val email = binding.userEmail
        val password = binding.userPassword

        binding.loginButton.setOnClickListener {
            if (email.text.toString().isNullOrEmpty() || password.text.toString().isNullOrEmpty()) {
                Toast.makeText(
                    this.requireContext(),
                    "Email and password can not be empty!",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                auth.signInWithEmailAndPassword(email.text.toString(), password.text.toString())
                    .addOnCompleteListener(this.requireActivity()) { task ->
                        if (task.isSuccessful) {
                            val preferences = requireContext().getSharedPreferences("WaterApp",
                                AppCompatActivity.MODE_PRIVATE)

                            val day = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
                            if (preferences.getInt("lastSign", 8) > day) {
                                findNavController().navigate(LoginFragmentDirections
                                    .actionLoginFragmentToQuizFragment())
                            } else {
                                startActivity(Intent(requireActivity(), MainActivity::class.java))
                            }
                        } else {
                            Toast.makeText(
                                this.requireContext(),
                                "User does not exist! Try again!",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
            }
        }

        return root
    }
}