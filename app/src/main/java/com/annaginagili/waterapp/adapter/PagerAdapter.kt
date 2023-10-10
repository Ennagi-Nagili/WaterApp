package com.annaginagili.waterapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.recyclerview.widget.RecyclerView
import com.annaginagili.waterapp.R
import com.annaginagili.waterapp.databinding.InfoPagerLayoutBinding
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import kotlin.math.floor

class PagerAdapter(private val context: Context):
    RecyclerView.Adapter<PagerAdapter.ItemHolder>() {
    private lateinit var listener: OnItemClickListener
    private lateinit var listener2: OnItemClickListener

    class ItemHolder(private val binding: InfoPagerLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun setData(context: Context, listener: OnItemClickListener, listener2: OnItemClickListener) {
            val imageList1 = mutableListOf(R.drawable.a1955, R.drawable.a1956, R.drawable.a1957,
                R.drawable.a1958, R.drawable.a1959, R.drawable.a1960, R.drawable.a1961, R.drawable.a1962,
                R.drawable.a1963, R.drawable.a1964, R.drawable.a1965, R.drawable.a1966, R.drawable.a1967,
                R.drawable.a1968, R.drawable.a1969, R.drawable.a1970, R.drawable.a1971, R.drawable.a1972,
                R.drawable.a1973, R.drawable.a1974, R.drawable.a1975, R.drawable.a1976, R.drawable.a1977,
                R.drawable.a1978, R.drawable.a1979, R.drawable.a1980, R.drawable.a1981, R.drawable.a1982,
                R.drawable.a1983, R.drawable.a1984, R.drawable.a1985, R.drawable.a1986, R.drawable.a1987,
                R.drawable.a1988, R.drawable.a1989, R.drawable.a1990, R.drawable.a1991, R.drawable.a1992,
                R.drawable.a1993, R.drawable.a1994, R.drawable.a1995, R.drawable.a1996, R.drawable.a1997,
                R.drawable.a1998, R.drawable.a1999, R.drawable.a2000, R.drawable.a2001, R.drawable.a2002,
                R.drawable.a2003, R.drawable.a2004, R.drawable.a2005, R.drawable.a2006, R.drawable.a2007,
                R.drawable.a2008, R.drawable.a2009, R.drawable.a2010, R.drawable.a2011, R.drawable.a2012,
                R.drawable.a2013, R.drawable.a2014, R.drawable.a2015, R.drawable.a2016)

            val imageList2 = mutableListOf(R.drawable.b1, R.drawable.b2, R.drawable.b3, R.drawable.b4,
                R.drawable.b5, R.drawable.b6, R.drawable.b7, R.drawable.b8, R.drawable.b9, R.drawable.b10,
                R.drawable.b11, R.drawable.b12, R.drawable.b13, R.drawable.b14, R.drawable.b15, R.drawable.b16,
                R.drawable.b17, R.drawable.b18, R.drawable.b19, R.drawable.b2, R.drawable.b21, R.drawable.b22,
                R.drawable.b23, R.drawable.b24, R.drawable.b25, R.drawable.b26, R.drawable.b27, R.drawable.b28)

            val imageList = if (adapterPosition == 0) {
                binding.image.setBackgroundResource(R.drawable.a1955)
                binding.back.visibility = View.INVISIBLE
                imageList1
            } else {
                binding.image.setBackgroundResource(R.drawable.b1)
                binding.back.visibility = View.VISIBLE
                imageList2
            }

            val count = if (adapterPosition == 0) {
                62.0
            } else {
                28.0
            }

            val label = if (adapterPosition == 0) {
                "Ocean warming"
            } else {
                "Sea level"
            }

            val point = HashMap<Float, Float>()

            for (i in 1..count.toInt()) {
                point[i.toFloat()] = i.toFloat()
            }

            val color = if (adapterPosition == 0) {
                R.color.red
            } else {
                R.color.blue
            }

            createChart(point, label, context, color)

            binding.seek.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
                override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                    if (p1 != 100) {
                        val index = floor(count / 100 * p1).toInt()
                        binding.image.setImageResource(imageList[index])
                    }
                }

                override fun onStartTrackingTouch(p0: SeekBar?) {

                }

                override fun onStopTrackingTouch(p0: SeekBar?) {

                }
            })

            binding.next.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }

            binding.back.setOnClickListener {
                listener2.onItemClick(adapterPosition)
            }
        }

        private fun createChart(points: HashMap<Float, Float>, label: String, context: Context, color: Int) {
            val entries:ArrayList<BarEntry> = ArrayList()

            for (i in points) {
                entries.add(BarEntry(i.key, i.value))
            }

            val barDataSet = BarDataSet(entries, label)
            barDataSet.color = context.resources.getColor(color)
            barDataSet.valueTextColor = context.resources.getColor(R.color.black)

            val dataSets: ArrayList<IBarDataSet> = ArrayList()
            dataSets.add(barDataSet)

            val barData = BarData(dataSets)
            binding.barChart.data = barData
            binding.barChart.description.isEnabled = false
            binding.barChart.xAxis.isEnabled = true
            binding.barChart.axisRight.isEnabled = false
            binding.barChart.setTouchEnabled(true)
            binding.barChart.setPinchZoom(true)

            // Refresh the chart to display the data points
            binding.barChart.invalidate()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val binding = InfoPagerLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemHolder(binding)
    }

    override fun getItemCount(): Int {
        return 2
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.setData(context, listener, listener2)
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    fun setOnItemClickListener2(listener: OnItemClickListener) {
        this.listener2 = listener
    }
}