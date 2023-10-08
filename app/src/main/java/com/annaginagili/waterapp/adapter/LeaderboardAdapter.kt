package com.annaginagili.waterapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.annaginagili.waterapp.R
import com.annaginagili.waterapp.model.Person

class LeaderboardAdapter(var data: MutableList<Person>) :
    RecyclerView.Adapter<LeaderboardAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userNameText: TextView = itemView.findViewById(R.id.userNameText)
        val userPoints: TextView = itemView.findViewById(R.id.userPoints)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LeaderboardAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.leaderboard_row_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.userNameText.text = data[position].name
        holder.userPoints.text = data[position].points.toString()
    }
}