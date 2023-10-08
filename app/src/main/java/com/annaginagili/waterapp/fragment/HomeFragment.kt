package com.annaginagili.waterapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.annaginagili.waterapp.R
import com.annaginagili.waterapp.model.Person
import com.annaginagili.waterapp.adapter.LeaderboardAdapter
import com.annaginagili.waterapp.adapter.TipsAdapter
import com.annaginagili.waterapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    private var tipsData = arrayListOf(
        "Promptly repair any leaks in faucets, pipes, or toilets.",
        "Replace old toilets, showerheads, and faucets with low-flow or WaterSense-labeled fixtures.",
        "Use the \"stopper\" in the sink when washing dishes by hand.",
        "When traveling, be mindful of water usage in areas prone to water scarcity.",
        "Keep an eye out for water-related problems in your community.",
        "Encourage industries to implement water-efficient technologies and practices.",
        "Only run the dishwasher and washing machine with full loads.",
        "Collect rainwater for outdoor use, such as watering plants."
    )

    private var people = mutableListOf(
        Person("John", 25),
        Person("Alice", 30),
        Person("Bob", 42),
        Person("Emma", 35),
        Person("David", 50),
        Person("Olivia", 28),
        Person("Michael", 37),
        Person("Sophia", 48),
        Person("Ethan", 22),
        Person("Ava", 40),
        Person("James", 33),
        Person("Mia", 29),
        Person("William", 45),
        Person("Emily", 27),
        Person("Benjamin", 39),
        Person("Amelia", 31),
        Person("Lucas", 36),
        Person("Charlotte", 43),
        Person("Alexander", 47),
        Person("Grace", 26),
        Person("Daniel", 38),
        Person("Lily", 41),
        Person("Matthew", 32),
        Person("Harper", 44),
        Person("Henry", 34),
        Person("Ella", 46),
        Person("Jackson", 49),
        Person("Avery", 24),
        Person("Scarlett", 23)
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        val tipsRecyclerView = binding.tipsRecyclerView
        tipsRecyclerView.adapter = TipsAdapter(tipsData)
        tipsRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        val leaderBoardAdapter = binding.leaderBoardRecyclerView
        leaderBoardAdapter.adapter = LeaderboardAdapter(people)
        leaderBoardAdapter.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        return binding.root
    }
}