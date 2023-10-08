package com.annaginagili.waterapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.annaginagili.waterapp.adapter.ChallengeAdapter
import com.annaginagili.waterapp.adapter.InfoAdapter
import com.annaginagili.waterapp.adapter.PagerAdapter
import com.annaginagili.waterapp.databinding.FragmentInfoBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class InfoFragment : Fragment() {
    private var _binding: FragmentInfoBinding? = null
    lateinit var pager: ViewPager2
    lateinit var tabLayout: TabLayout

    private val binding get() = _binding!!
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {

        _binding = FragmentInfoBinding.inflate(inflater, container, false)
        val root: View = binding.root
        pager = binding.pager
        tabLayout = binding.tab

        pager.adapter = InfoAdapter(childFragmentManager, requireActivity().lifecycle)

        val list = listOf("Statistics", "News")

        TabLayoutMediator(tabLayout, pager) { tab, position ->
            tab.text = list[position]
        }.attach()

        return root
    }
}