package com.annaginagili.waterapp.activity.onboard_ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.annaginagili.waterapp.activity.MainActivity
import com.annaginagili.waterapp.databinding.FragmentSignupBinding
import com.google.firebase.auth.FirebaseAuth


class SignupFragment : Fragment() {


    private var _binding: FragmentSignupBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignupBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val auth = FirebaseAuth.getInstance()

        binding.loginRedirect.setOnClickListener {
            findNavController().navigate(SignupFragmentDirections.actionSignupFragmentToLoginFragment())
        }

        val email = binding.userEmail
        val password = binding.userPassword

        binding.appCompatButton.setOnClickListener {
            if (email.text.toString().isNullOrEmpty() || password.text.toString().isNullOrEmpty()) {
                Toast.makeText(
                    this.requireContext(),
                    "Email and password can not be empty!",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                auth.createUserWithEmailAndPassword(email.text.toString(), password.text.toString())
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            auth.signOut()
                            findNavController().navigate(SignupFragmentDirections
                                .actionSignupFragmentToLoginFragment())
                        } else {
                            Toast.makeText(
                                this.requireContext(),
                                "Something went wrong! Try again!",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
            }
        }

        return root
    }


}