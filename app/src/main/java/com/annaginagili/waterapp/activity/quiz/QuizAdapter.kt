package com.annaginagili.waterapp.activity.quiz

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.annaginagili.waterapp.R
import com.annaginagili.waterapp.activity.MainActivity
import com.annaginagili.waterapp.activity.onboard_ui.SplashFragmentDirections
import com.google.android.material.floatingactionbutton.FloatingActionButton

class QuizAdapter(private val quizzes:ArrayList<Quiz>,private val viewPager2: ViewPager2,private val context:Context)
    : RecyclerView.Adapter<QuizAdapter.CardViewHolder>(){

    class CardViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val question:TextView = itemView.findViewById(R.id.question)
        val answer1: RadioButton = itemView.findViewById(R.id.radio_first_option)
        val answer2:RadioButton = itemView.findViewById(R.id.radio_second_option)
        val answer3:RadioButton = itemView.findViewById(R.id.radio_third_option)

        val fab_back:FloatingActionButton = itemView.findViewById(R.id.fab_back)
        val fab_forward:FloatingActionButton = itemView.findViewById(R.id.fab_forward)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.quiz_item,parent,false)
        return CardViewHolder(view)
    }

    override fun getItemCount(): Int {
        return quizzes.size
    }

    @SuppressLint("CommitPrefEdits")
    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val quiz = quizzes[position]
        holder.question.text = quiz.question


        val sharedPref = context.getSharedPreferences("QUIZ_RESPONSES",Context.MODE_PRIVATE)
        var editor = sharedPref.edit()

        if (quiz.answers.size == 2)
        {
            holder.answer1.text = quiz.answers[0]
            holder.answer2.text = quiz.answers[1]
            holder.answer3.visibility = View.INVISIBLE
        }else{
            holder.answer1.text = quiz.answers[0]
            holder.answer2.text = quiz.answers[1]
            holder.answer3.text = quiz.answers[2]
        }


        holder.answer1.setOnCheckedChangeListener { _, _ ->
            editor.putString("quiz_${quiz.number}",quiz.answers[0])
            editor.commit()
            if (viewPager2.currentItem < quizzes.size - 1)
            {

                val handler = Handler(Looper.getMainLooper())
                handler.postDelayed({
                    viewPager2.currentItem  = position + 1
                }, 1000)
            }else{
                val handler = Handler(Looper.getMainLooper())
                handler.postDelayed({
                    val intent = Intent(context,MainActivity::class.java)
                    context.startActivity(intent)
                }, 1000)
            }
        }

        holder.answer2.setOnCheckedChangeListener { _, _ ->
            editor.putString("quiz_${quiz.number}",quiz.answers[1])
            editor.commit()
            if (viewPager2.currentItem < quizzes.size - 1)
            {
                val handler = Handler(Looper.getMainLooper())
                handler.postDelayed({
                    viewPager2.currentItem  = position + 1
                }, 1000)

            }else{
                val handler = Handler(Looper.getMainLooper())
                handler.postDelayed({
                    val intent = Intent(context,MainActivity::class.java)
                    context.startActivity(intent)
                }, 1000)
            }
        }

        holder.answer3.setOnCheckedChangeListener { _, _ ->
            editor.putString("quiz_${quiz.number}",quiz.answers[2])
            editor.commit()
            if (viewPager2.currentItem < quizzes.size - 1)
            {

                val handler = Handler(Looper.getMainLooper())
                handler.postDelayed({
                    viewPager2.currentItem  = position + 1
                }, 1000)

            }else{
                val handler = Handler(Looper.getMainLooper())
                handler.postDelayed({
                    val intent = Intent(context,MainActivity::class.java)
                    context.startActivity(intent)
                }, 1000)
            }
        }
    }
}