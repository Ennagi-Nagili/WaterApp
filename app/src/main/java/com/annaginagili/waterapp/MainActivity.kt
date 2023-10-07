package com.annaginagili.waterapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import com.annaginagili.waterapp.databinding.ActivityMainBinding

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

        val imageList = arrayListOf(R.drawable.a1955,R.drawable.a1956, R.drawable.a1957, R.drawable.a1958,
            R.drawable.a1959, R.drawable.a1960, R.drawable.a1961, R.drawable.a1962, R.drawable.a1963, R.drawable.a1964)

        seek.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                Log.e("hello", p1.toString())
                if (p1 != 100) {
                    image.setBackgroundResource(imageList[p1 / 10])
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }
        })
    }
}