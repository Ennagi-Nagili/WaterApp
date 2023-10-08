package com.annaginagili.waterapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.annaginagili.waterapp.adapter.PagerAdapter
import com.annaginagili.waterapp.databinding.FragmentInfoBinding

class InfoFragment : Fragment() {
    private var _binding: FragmentInfoBinding? = null
    lateinit var pager: ViewPager2

    private val binding get() = _binding!!
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {

        _binding = FragmentInfoBinding.inflate(inflater, container, false)
        val root: View = binding.root
        pager = binding.pager

        pager.adapter = PagerAdapter(requireContext())

        return root
    }
}