package com.annaginagili.waterapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.annaginagili.waterapp.R
import com.annaginagili.waterapp.adapter.MyChallengeAdapter
import com.annaginagili.waterapp.databinding.FragmentMyChallengesBinding

class MyChallenges : Fragment() {
    lateinit var binding: FragmentMyChallengesBinding
    lateinit var recycler: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = FragmentMyChallengesBinding.inflate(inflater)
        recycler = binding.recycler

        recycler.setHasFixedSize(true)
        recycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        val challengeList = listOf("Identify and fix any leaks in your home",
            "Set up a rainwater harvesting system to collect rainwater for outdoor use")

        recycler.adapter = MyChallengeAdapter(requireContext(), challengeList)

        return binding.root
    }
}