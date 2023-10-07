package com.annaginagili.waterapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import com.annaginagili.waterapp.databinding.ActivityMainBinding
import kotlin.math.floor

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var image: ImageView
    lateinit var seek: SeekBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        image = binding.image
        seek = binding.seek

        val imageList = arrayListOf(R.drawable.a1955, R.drawable.a1956, R.drawable.a1957, R.drawable.a1958,
            R.drawable.a1959, R.drawable.a1960, R.drawable.a1961, R.drawable.a1962, R.drawable.a1963,
            R.drawable.a1964, R.drawable.a1965, R.drawable.a1966, R.drawable.a1967, R.drawable.a1968,
            R.drawable.a1969, R.drawable.a1970, R.drawable.a1971, R.drawable.a1972, R.drawable.a1973,
            R.drawable.a1974, R.drawable.a1975, R.drawable.a1976, R.drawable.a1977, R.drawable.a1978,
            R.drawable.a1979, R.drawable.a1980, R.drawable.a1981, R.drawable.a1982, R.drawable.a1983,
            R.drawable.a1984, R.drawable.a1985, R.drawable.a1986, R.drawable.a1987, R.drawable.a1988,
            R.drawable.a1989, R.drawable.a1990, R.drawable.a1991, R.drawable.a1992, R.drawable.a1993,
            R.drawable.a1994, R.drawable.a1995, R.drawable.a1996, R.drawable.a1997, R.drawable.a1998,
            R.drawable.a1999, R.drawable.a2000, R.drawable.a2001, R.drawable.a2002, R.drawable.a2003,
            R.drawable.a2004, R.drawable.a2005, R.drawable.a2006, R.drawable.a2007, R.drawable.a2008,
            R.drawable.a2009, R.drawable.a2010, R.drawable.a2011, R.drawable.a2012, R.drawable.a2013,
            R.drawable.a2014, R.drawable.a2015, R.drawable.a2016)

        seek.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                if (p1 != 100) {
                    image.setBackgroundResource(imageList[floor(62.0 / 100 * p1).toInt()])
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }
        })
    }
}