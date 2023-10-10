package com.annaginagili.waterapp.fragment.myChallenges

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.annaginagili.waterapp.adapter.MyChallengeAdapter
import com.annaginagili.waterapp.databinding.FragmentMyChallengesBinding
import com.annaginagili.waterapp.room.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyChallenges : Fragment() {
    lateinit var binding: FragmentMyChallengesBinding
    lateinit var recycler: RecyclerView
    lateinit var viewModel: MyChallengesViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = FragmentMyChallengesBinding.inflate(inflater)
        recycler = binding.recycler
        viewModel = ViewModelProvider(this)[MyChallengesViewModel::class.java]

        recycler.setHasFixedSize(true)
        recycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

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
            val taskList = ArrayList<ArrayList<String>>()
            val taskList1 = mutableListOf("Save water", "Inform someone about water shortage",
                "Clean a water basin")
            val taskList2 = mutableListOf("Inform someone about water shortage", "Clean a water basin",
                "Save water")
            val taskList3 = mutableListOf("Clean a water basin", "Inform someone about water shortage",
                "Save water")

            var count = 1

            for (i in it.indices) {
                when (count) {
                    1 -> {
                        taskList.add(taskList1 as ArrayList<String>)
                        count ++
                    }
                    2 -> {
                        taskList.add(taskList2 as ArrayList<String>)
                        count ++
                    }
                    else -> {
                        taskList.add(taskList3 as ArrayList<String>)
                        count = 1
                    }
                }
            }

            val adapter = MyChallengeAdapter(requireContext(), it, taskList)
            recycler.adapter = adapter
        }
    }

    override fun onResume() {
        super.onResume()
        CoroutineScope(Dispatchers.Default).launch {
            val db = Room.databaseBuilder(requireContext(), AppDatabase::class.java, "Task")
                .build()
            viewModel.getChallenges(db.taskDao())
        }
    }
}