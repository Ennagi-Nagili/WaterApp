package com.annaginagili.waterapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.annaginagili.waterapp.databinding.ChallengeLayoutBinding
import com.annaginagili.waterapp.databinding.FindLayoutBinding
import com.annaginagili.waterapp.model.Challenge

class FindAdapter(private val context: Context, private val itemList: List<Challenge>):
    RecyclerView.Adapter<FindAdapter.ItemHolder>() {
    private lateinit var listener: OnItemClickListener

    class ItemHolder(private val binding: FindLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun setData(item: Challenge, listener: OnItemClickListener) {
            binding.text.text = item.text
            binding.duration.text = "Duration: " + item.duration.toString() + " days"
            binding.point.text = "Points: " + item.point.toString() + " points"

            binding.add.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val binding = FindLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemHolder(binding)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.setData(itemList[position], listener)
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}