package com.example.to_doapp
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TaskViewModel : ViewModel() {
    private val _taskList = MutableLiveData<MutableList<Task>>(mutableListOf())
    val taskList: LiveData<MutableList<Task>> get() = _taskList

    init {
        _taskList.value = mutableListOf(
            Task("Praying and reading Quran", "Commit to praying on time and reading the daily Quranic portion"),

        )
    }

    fun addTask(task: Task) {
        _taskList.value?.let {
            it.add(task)
            _taskList.value = it
        }
    }

    fun updateTask(position: Int, updatedTask: Task) {
        _taskList.value?.let {
            if (position in it.indices) {
                it[position] = updatedTask
                _taskList.value = it
            } else {
                Log.d("TaskViewModel", "Invalid position: $position")
            }
        }
    }

    fun removeTask(task: Task) {
        _taskList.value?.let {
            if (it.remove(task)) {
                Log.d("TaskViewModel", "Removed task: $task")
                _taskList.value = it
            } else {
                Log.d("TaskViewModel", "Failed to remove task: $task")
            }
        }
    }
}