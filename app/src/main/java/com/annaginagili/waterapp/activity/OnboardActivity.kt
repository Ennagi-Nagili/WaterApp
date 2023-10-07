package com.annaginagili.waterapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.annaginagili.waterapp.databinding.ActivityOnboardBinding

class OnboardActivity : AppCompatActivity() {

    private lateinit var binding:ActivityOnboardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}