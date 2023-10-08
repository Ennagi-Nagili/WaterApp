package com.annaginagili.waterapp.activity.onboard_ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.fragment.findNavController
import com.annaginagili.waterapp.activity.MainActivity
import com.annaginagili.waterapp.databinding.FragmentLoginBinding
import com.annaginagili.waterapp.databinding.FragmentSignupBinding
import com.google.firebase.auth.FirebaseAuth

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

        if (auth.currentUser != null) {
            val intent = Intent(requireContext(), MainActivity::class.java)
            startActivity(intent)
        }



        binding.signupRedirect.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToSignupFragment()
            findNavController().navigate(action)
        }

        // Temporary, after testing this block should be deleted
        binding.loginButton.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToQuizFragment())
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
                val auth = FirebaseAuth.getInstance()
                auth.signInWithEmailAndPassword(email.text.toString(), password.text.toString())
                    .addOnCompleteListener(this.requireActivity()) { task ->
                        if (task.isSuccessful) {

                            val intent = Intent(this.requireContext(), MainActivity::class.java)
                            startActivity(intent)

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