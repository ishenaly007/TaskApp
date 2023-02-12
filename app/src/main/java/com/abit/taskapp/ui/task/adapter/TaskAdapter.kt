package com.abit.taskapp.ui.task.adapter

import android.location.GnssAntennaInfo.Listener
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.abit.taskapp.App
import com.abit.taskapp.databinding.ItemTaskBinding
import com.abit.taskapp.model.Task

class TaskAdapter(private val onClick: (Task) -> Unit, private val onClickUpdate: (Task) -> Unit):Adapter<TaskAdapter.TaskViewHolder>() {
    private val data = arrayListOf<Task>()
    fun addTask(task: Task){
        data.add(0,task)
        notifyItemChanged(0)
    }
    fun addTasks(list: List<Task>){
        data.clear()
        data.addAll(list)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(ItemTaskBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class TaskViewHolder(private val binding: ItemTaskBinding):ViewHolder(binding.root){
        fun bind(task: Task) {
            itemView.setOnLongClickListener() {
                onClick(task)
                false
            }
            itemView.setOnClickListener{
                onClickUpdate(task)
            }
            binding.tvTitleTask.text = task.title
            binding.tvDescTask.text = task.desc
        }

    }


}




