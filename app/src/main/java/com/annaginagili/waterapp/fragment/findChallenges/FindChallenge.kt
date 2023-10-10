package com.annaginagili.waterapp.fragment.findChallenges

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.annaginagili.waterapp.R
import com.annaginagili.waterapp.adapter.FindAdapter
import com.annaginagili.waterapp.databinding.FragmentFindChallengeBinding
import com.annaginagili.waterapp.fragment.ChallengeFragment
import com.annaginagili.waterapp.fragment.myChallenges.MyChallenges
import com.annaginagili.waterapp.room.AppDatabase
import com.annaginagili.waterapp.model.Challenge
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FindChallenge : Fragment() {
    lateinit var binding: FragmentFindChallengeBinding
    lateinit var recycler: RecyclerView
    lateinit var viewModel: FindChallengeViewModel
    lateinit var challengeList: ArrayList<Challenge>
    lateinit var adapter: FindAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFindChallengeBinding.inflate(inflater)
        recycler = binding.recycler
        viewModel = ViewModelProvider(this)[FindChallengeViewModel::class.java]

        recycler.setHasFixedSize(true)
        recycler.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        val dfChecks = "0.0.0"
        challengeList = arrayListOf(
            Challenge(0, "Identify and fix any leaks in your home", 0, 10, 5, dfChecks),
            Challenge(0, "Set up a rainwater harvesting system to collect rainwater for outdoor use", 0, 10, 7, dfChecks),
            Challenge(0, "Monitor your daily water consumption", 0, 15, 7, dfChecks),
            Challenge(0, "Make it a habit to turn off the tap while brushing your teeth", 0, 10, 5, dfChecks),
            Challenge(0, "Educate your friends and family about water scarcity", 0, 15, 7, dfChecks),
            Challenge(0, "Find the leaks outside and report them to the municipality", 0, 20, 10, dfChecks),
            Challenge(0, "Join local river or beach cleanup events to prevent water pollution.", 0, 10, 5, dfChecks),
            Challenge(0, "Replace standard showerheads and faucets with low-flow or WaterSense-labeled fixtures.", 0, 10, 5, dfChecks),
            Challenge(0, "Reduce the number of times you shower at least once", 0, 20, 10, dfChecks),
            Challenge(0, "Advocate for water conservation policies in your community", 0, 15, 7, dfChecks),
            Challenge(0, "Challenge yourself to limit your shower time to 5 minutes or less", 0, 10, 5, dfChecks),
            Challenge(0, "Set your time limit to under 1 minute for washing your hands", 0, 20, 10, dfChecks)
        )

        CoroutineScope(Dispatchers.Default).launch {
            val db = Room.databaseBuilder(requireContext(), AppDatabase::class.java, "Task")
                .build()
            viewModel.getChallenges(db.taskDao())
        }

        setObserver()

        return binding.root
    }

    private fun setObserver() {
        viewModel.observeChallenges().observe(viewLifecycleOwner) {
            val chList = ArrayList<Challenge>()

            for (i in challengeList) {
                var check = false

                for (j in it) {
                    if (i.text == j.text) {
                        check = true
                    }
                }

                if (!check) {
                    chList.add(i)
                }
            }

            adapter = FindAdapter(requireContext(), chList)

            adapter.setOnItemClickListener(object : FindAdapter.OnItemClickListener {
                override fun onItemClick(position: Int) {
                    CoroutineScope(Dispatchers.Default).launch {
                        val db =
                            Room.databaseBuilder(requireContext(), AppDatabase::class.java, "Task")
                                .build()
                        viewModel.addChallenge(chList[position], db.taskDao())
                        chList.removeAt(position)
                    }
                }
            })

            recycler.adapter = adapter
        }

        setAddObserver()
    }

    private fun setAddObserver() {
        viewModel.observeAdd().observe(viewLifecycleOwner) {
            adapter.notifyDataSetChanged()
        }
    }
}