package com.annaginagili.waterapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.annaginagili.waterapp.R
import com.annaginagili.waterapp.adapter.PagerAdapter
import com.annaginagili.waterapp.databinding.FragmentInfoBinding
import com.annaginagili.waterapp.databinding.FragmentStatiscsBinding

class StatiscsFragment : Fragment() {
    private var _binding: FragmentStatiscsBinding? = null
    lateinit var pager: ViewPager2

    private val binding get() = _binding!!
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        _binding = FragmentStatiscsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        pager = binding.pager

        pager.isUserInputEnabled = false

        val adapter = PagerAdapter(requireContext())

        adapter.setOnItemClickListener(object : PagerAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                pager.currentItem = 1
            }
        })

        adapter.setOnItemClickListener2(object : PagerAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                pager.currentItem = 0
            }
        })

        pager.adapter = adapter

        return root
    }
}