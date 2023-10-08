package com.annaginagili.waterapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.annaginagili.waterapp.R
import com.annaginagili.waterapp.adapter.FindAdapter
import com.annaginagili.waterapp.adapter.MyChallengeAdapter
import com.annaginagili.waterapp.databinding.FragmentFindChallengeBinding

class FindChallenge : Fragment() {
    lateinit var binding: FragmentFindChallengeBinding
    lateinit var recycler: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = FragmentFindChallengeBinding.inflate(inflater)
        recycler = binding.recycler

        recycler.setHasFixedSize(true)
        recycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        val challengeList = listOf("Monitor your daily water consumption",
            "Make it a habit to turn off the tap while brushing your teeth",
            "Educate your friends and family about water scarcity",
            "Find the leaks outside and report them to the municipality",
            "Join local river or beach cleanup events to prevent water pollution.",
            "Replace standard showerheads and faucets with low-flow or WaterSense-labeled fixtures.",
            "Reduce the number of times you shower at least once",
            "Advocate for water conservation policies in your community",
            "Challenge yourself to limit your shower time to 5 minutes or less",
            "Set your time limit to under 1 minute for washing your hands")

        val adapter = FindAdapter(requireContext(), challengeList)

        adapter.setOnItemClickListener(object : FindAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {

            }
        })

        recycler.adapter = adapter

        return binding.root
    }
}