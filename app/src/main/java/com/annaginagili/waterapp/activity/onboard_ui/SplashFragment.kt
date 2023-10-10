package com.annaginagili.waterapp.activity.onboard_ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.annaginagili.waterapp.activity.MainActivity
import com.annaginagili.waterapp.databinding.FragmentSplashBinding
import com.google.firebase.auth.FirebaseAuth
import java.util.Calendar


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
            val preferences = requireContext().getSharedPreferences("WaterApp",
                AppCompatActivity.MODE_PRIVATE)

            //preferences.edit().putInt("lastSign", 8).apply()

            if (FirebaseAuth.getInstance().currentUser != null) {
                val day = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
                if (preferences.getInt("lastSign", 8) > day - 1) {
                    findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToQuizFragment())
                } else {
                    startActivity(Intent(requireActivity(), MainActivity::class.java))
                }
            } else {
                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToLoginFragment())
            }
        }, 2000)




        return root
    }
}