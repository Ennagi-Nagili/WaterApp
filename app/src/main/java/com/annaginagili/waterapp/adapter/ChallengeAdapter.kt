package com.annaginagili.waterapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.annaginagili.waterapp.fragment.ChallengeFragment
import com.annaginagili.waterapp.fragment.FindChallenge
import com.annaginagili.waterapp.fragment.MyChallenges

class ChallengeAdapter(fm: FragmentManager, lifecycle: Lifecycle):
   FragmentStateAdapter(fm, lifecycle) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> MyChallenges()
            else -> FindChallenge()
        }
    }
}