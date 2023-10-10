package com.annaginagili.waterapp.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.annaginagili.waterapp.R
import com.annaginagili.waterapp.databinding.ChallengeLayoutBinding
import com.annaginagili.waterapp.model.Challenge
import com.annaginagili.waterapp.room.AppDatabase
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MyChallengeAdapter(private val context: Context, private val itemList: List<Challenge>,
                         private val taskList: List<List<String>>):
    RecyclerView.Adapter<MyChallengeAdapter.ItemHolder>() {
    class ItemHolder(private val binding: ChallengeLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun setData(item: Challenge, context: Context, taskList: List<String>) {
            binding.text.text = item.text
            binding.duration.text = "Duration: " + item.duration.toString() + " days"
            binding.point.text = "Points: " + item.point.toString() + " points"
            binding.progress.progress = item.progress

            binding.root.setOnClickListener {
                if (binding.task.visibility == View.GONE) {
                    binding.task.visibility = View.VISIBLE
                } else {
                    binding.task.visibility = View.GONE
                }
            }

            val task = binding.task
            task.setHasFixedSize(true)
            task.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

            var checks = item.checks

            val adapter = TaskAdapter(context, taskList, checks)

            adapter.setOnItemClickListener(object : TaskAdapter.OnItemClickListener {
                override fun onItemClick(position: Int, check: Boolean) {
                    CoroutineScope(Dispatchers.Default).launch {
                        val db = Room.databaseBuilder(context, AppDatabase::class.java, "Task")
                            .build()
                        if (check) {
                            binding.progress.progress += (100.0 / taskList.size).toInt()
                            checks = checks.replaceRange(position*2, position*2 + 1, "1")
                        } else {
                            binding.progress.progress -= (100.0 / taskList.size).toInt()
                            checks = checks.replaceRange(position*2, position*2 + 1, "0")
                        }
                        item.progress = binding.progress.progress
                        Log.e("checks", checks)
                        item.checks = checks
                        db.taskDao().edit(item)
                    }
                }
            })

            task.adapter = adapter
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
        holder.setData(itemList[position], context, taskList[position])
    }
}