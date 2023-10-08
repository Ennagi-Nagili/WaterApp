package com.annaginagili.waterapp.fragment.findChallenges

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.annaginagili.waterapp.adapter.FindAdapter
import com.annaginagili.waterapp.databinding.FragmentFindChallengeBinding
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
    lateinit var challengeList: ArrayList<String>
    lateinit var chgList: ArrayList<String>
    lateinit var adapter: FindAdapter
    val myChallenges = MyChallenges()

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

        challengeList = arrayListOf(
            "Identify and fix any leaks in your home",
            "Set up a rainwater harvesting system to collect rainwater for outdoor use",
            "Monitor your daily water consumption",
            "Make it a habit to turn off the tap while brushing your teeth",
            "Educate your friends and family about water scarcity",
            "Find the leaks outside and report them to the municipality",
            "Join local river or beach cleanup events to prevent water pollution.",
            "Replace standard showerheads and faucets with low-flow or WaterSense-labeled fixtures.",
            "Reduce the number of times you shower at least once",
            "Advocate for water conservation policies in your community",
            "Challenge yourself to limit your shower time to 5 minutes or less",
            "Set your time limit to under 1 minute for washing your hands"
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
            val list = ArrayList<String>()

            for (i in it) {
                list.add(i.text)
            }

            chgList = ArrayList<String>()

            for (i in challengeList) {
                if (i !in list) {
                    chgList.add(i)
                }
            }

            adapter = FindAdapter(requireContext(), chgList)

            adapter.setOnItemClickListener(object : FindAdapter.OnItemClickListener {
                override fun onItemClick(position: Int) {
                    CoroutineScope(Dispatchers.Default).launch {
                        val db =
                            Room.databaseBuilder(requireContext(), AppDatabase::class.java, "Task")
                                .build()
                        viewModel.addChallenge(Challenge(0, chgList[position]), db.taskDao())
                        myChallenges.adapter.addData(chgList[position])
                        chgList.removeAt(position)
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