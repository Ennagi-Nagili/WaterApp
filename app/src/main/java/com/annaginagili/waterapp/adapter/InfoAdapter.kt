package com.annaginagili.waterapp.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.annaginagili.waterapp.fragment.NewsFragment
import com.annaginagili.waterapp.fragment.StatiscsFragment
import com.annaginagili.waterapp.fragment.findChallenges.FindChallenge
import com.annaginagili.waterapp.fragment.myChallenges.MyChallenges

class InfoAdapter(fm: FragmentManager, lifecycle: Lifecycle):
    FragmentStateAdapter(fm, lifecycle) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> StatiscsFragment()
            else -> NewsFragment()
        }
    }
}