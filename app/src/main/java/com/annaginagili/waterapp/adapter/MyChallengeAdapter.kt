package com.annaginagili.waterapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.annaginagili.waterapp.databinding.ChallengeLayoutBinding
import com.annaginagili.waterapp.fragment.MyChallenges

class MyChallengeAdapter(private val context: Context, private val itemList: List<String>):
    RecyclerView.Adapter<MyChallengeAdapter.ItemHolder>() {
    class ItemHolder(private val binding: ChallengeLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun setData(item: String) {
            binding.text.text = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val binding = ChallengeLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemHolder(binding)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.setData(itemList[position])
    }
}