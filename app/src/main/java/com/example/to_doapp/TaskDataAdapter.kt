package com.example.to_doapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.to_doapp.databinding.ItemTaskBinding

class TaskDataAdapter(
    private val list: MutableList<Task>,
    private val onItemClick: (Task, Int) -> Unit,
    private val onRemoveClick: (Task) -> Unit
) : RecyclerView.Adapter<TaskDataAdapter.MyHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyHolder(binding)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.bindData(list[position], position)
    }

    override fun getItemCount() = list.size

    inner class MyHolder(private val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(task: Task, position: Int) {
            binding.taskTitle.text = task.title
            binding.taskDescription.text = task.description
            binding.editButton.setOnClickListener {
                onItemClick(task, adapterPosition)
            }
            binding.removeButton.setOnClickListener {
                onRemoveClick(task)
            }
        }
    }

    fun updateList(newList: List<Task>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }
}