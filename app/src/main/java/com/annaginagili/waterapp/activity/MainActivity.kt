package com.annaginagili.waterapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.fragment.app.Fragment
import com.annaginagili.waterapp.R
import com.annaginagili.waterapp.databinding.ActivityMainBinding
import com.annaginagili.waterapp.fragment.ChallengeFragment
import com.annaginagili.waterapp.fragment.HomeFragment
import com.annaginagili.waterapp.fragment.InfoFragment
import kotlin.math.floor

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setCurrentFragment(InfoFragment())

        binding.bottom.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.info -> setCurrentFragment(InfoFragment())
                R.id.home -> setCurrentFragment(HomeFragment())
                R.id.contribution -> setCurrentFragment(ChallengeFragment())
            }

            true
        }
    }

    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(binding.container.id, fragment)
            commit()
        }
    }
}