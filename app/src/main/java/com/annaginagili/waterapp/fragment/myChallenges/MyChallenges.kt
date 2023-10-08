package com.annaginagili.waterapp.fragment.myChallenges

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
    var adapter = MyChallengeAdapter(arrayListOf())

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

//        CoroutineScope(Dispatchers.Default).launch {
//            val db = Room.databaseBuilder(requireContext(), AppDatabase::class.java, "Task")
//                .build()
//            db.taskDao().deleteAll()
//        }

        setObserver()

        return binding.root
    }

    private fun setObserver() {
        viewModel.observeChallenges().observe(viewLifecycleOwner) {
            val challengeList = ArrayList<String>()

            for (i in it) {
                challengeList.add(i.text)
            }

            adapter = MyChallengeAdapter(challengeList)
            recycler.adapter = adapter
        }
    }
}