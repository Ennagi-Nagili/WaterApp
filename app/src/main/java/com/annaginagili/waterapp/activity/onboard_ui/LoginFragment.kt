package com.annaginagili.waterapp.activity.onboard_ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.annaginagili.waterapp.databinding.FragmentSignupBinding

class LoginFragment : Fragment() {

    private var _binding: FragmentSignupBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignupBinding.inflate(inflater, container, false)
        val root: View = binding.root


        binding.appCompatButton.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToQuizFragment())
        }


        return root
    }

}