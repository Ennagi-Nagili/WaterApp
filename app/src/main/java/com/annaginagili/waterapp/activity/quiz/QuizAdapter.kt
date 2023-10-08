package com.annaginagili.waterapp.activity.quiz

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.annaginagili.waterapp.R

class QuizAdapter(private val quizzes:ArrayList<Quiz>)
    : RecyclerView.Adapter<QuizAdapter.CardViewHolder>(){

    class CardViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val question:TextView = itemView.findViewById(R.id.question)
        val answer1: RadioButton = itemView.findViewById(R.id.radio_first_option)
        val answer2:RadioButton = itemView.findViewById(R.id.radio_second_option)
        val answer3:RadioButton = itemView.findViewById(R.id.radio_third_option)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.quiz_item,parent,false)
        return CardViewHolder(view)
    }

    override fun getItemCount(): Int {
        return quizzes.size
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val quiz = quizzes[position]
        holder.question.text = quiz.question
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
    }
}