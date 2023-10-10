package com.annaginagili.waterapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import androidx.viewpager2.widget.ViewPager2
import com.annaginagili.waterapp.R
import com.annaginagili.waterapp.adapter.ChallengeAdapter
import com.annaginagili.waterapp.databinding.FragmentChallengeBinding
import com.annaginagili.waterapp.fragment.myChallenges.MyChallenges
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayoutMediator

class ChallengeFragment : Fragment() {
    lateinit var binding: FragmentChallengeBinding
    lateinit var pager: ViewPager2
    lateinit var tabLayout: TabLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = FragmentChallengeBinding.inflate(inflater)
        pager = binding.pager
        tabLayout = binding.tab

        pager.adapter = ChallengeAdapter(childFragmentManager, requireActivity().lifecycle)

        val list = listOf("My Challenges", "Find Challenge")

        TabLayoutMediator(tabLayout, pager) { tab, position ->
            tab.text = list[position]
        }.attach()

        return binding.root
    }
}