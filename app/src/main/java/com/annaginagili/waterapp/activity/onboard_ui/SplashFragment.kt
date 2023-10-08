package com.annaginagili.waterapp.activity.onboard_ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.annaginagili.waterapp.databinding.FragmentSplashBinding


class SplashFragment : Fragment() {

    private var _binding: FragmentSplashBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        val root: View = binding.root



        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToLoginFragment())
        }, 2000)




        return root
    }
}