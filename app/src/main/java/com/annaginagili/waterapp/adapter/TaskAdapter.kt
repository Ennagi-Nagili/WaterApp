package com.annaginagili.waterapp.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.annaginagili.waterapp.databinding.FindLayoutBinding
import com.annaginagili.waterapp.databinding.TaskLayoutBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class TaskAdapter(private val context: Context, private val itemList: List<String>, private val checks: String):
    RecyclerView.Adapter<TaskAdapter.ItemHolder>() {
    private lateinit var listener: OnItemClickListener

    class ItemHolder(private val binding: TaskLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun setData(item: String, listener: OnItemClickListener, checks: String) {
            binding.text.text = item

            Log.e("hello", checks)

            binding.check.isChecked = checks[adapterPosition*2] != '0'

            binding.check.setOnCheckedChangeListener { compoundButton, b ->
                listener.onItemClick(adapterPosition, b)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val binding = TaskLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemHolder(binding)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.setData(itemList[position], listener, checks)
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int, check: Boolean)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}