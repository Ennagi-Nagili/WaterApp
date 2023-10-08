package com.annaginagili.waterapp.activity.quiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.annaginagili.waterapp.R
import com.annaginagili.waterapp.databinding.FragmentQuizBinding
import com.annaginagili.waterapp.databinding.FragmentSignupBinding


class QuizFragment : Fragment() {

    private var _binding: FragmentQuizBinding? = null

    private val binding get() = _binding!!


    private val quizzes = ArrayList<Quiz>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuizBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setQuizQuestions()

        val transformer = CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(40))
        transformer.addTransformer{page,position->
            val r = 1 - kotlin.math.abs(position)
            page.scaleY = 0.85f + r*0.14f
        }

        binding.viewPager2.setPageTransformer(transformer)

        val adapter = QuizAdapter(quizzes)
        binding.viewPager2.adapter = adapter

        binding.viewPager2.offscreenPageLimit = 3
        binding.viewPager2.clipToPadding = false
        binding.viewPager2.clipChildren = false


        return root
    }



    private fun setQuizQuestions(){

        quizzes.add(Quiz(1,"How much do you worry about the lack of water and its impact on the environment?",
            listOf("I am aware of this and I am trying to reduce my water consumption to my minimum.","No, it doesn't bother me!")
        ))

        quizzes.add(Quiz(2,"How much water do you consume per day?",
            listOf("300-380 liters","380+ liters")
        ))

        quizzes.add(Quiz(3,"Do you turn off the tap while washing your hands?",
            listOf("Yes, I always turn off the tap when not actively using water.","No, I usually leave the tap running while washing my hands."
            ,"Sometimes, I turn off the tap,but not always.")
        ))

        quizzes.add(Quiz(4,"Do You inspire someone to save on water consumption?",
            listOf("I encourage everyone the importance of water for present and future generations.","No,I don't care.")
        ))
    }
}