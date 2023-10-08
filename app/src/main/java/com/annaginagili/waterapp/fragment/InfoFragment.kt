package com.annaginagili.waterapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.annaginagili.waterapp.R
import com.annaginagili.waterapp.activity.PagerAdapter
import com.annaginagili.waterapp.databinding.FragmentInfoBinding
import com.annaginagili.waterapp.databinding.FragmentSignupBinding
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet

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