package com.d121201098.test.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.d121201098.test.data.TaskDatabase
import com.d121201098.test.model.Task
import com.d121201098.test.repository.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskViewModel (application: Application):AndroidViewModel(application){
    val readAllData:LiveData<List<Task>>
    val readAllDataHistory:LiveData<List<Task>>

    private val repository:TaskRepository
    init {
        val taskDao = TaskDatabase.getDatabase(application).taskDao()
        repository = TaskRepository(taskDao)
        readAllData = repository.readAllData
        readAllDataHistory = repository.readAllDataHistory
    }

    fun addTask(task: Task){
        viewModelScope.launch (Dispatchers.IO){
            repository.addTask(task)
        }
    }
    fun updateTask(task: Task){
        viewModelScope.launch (Dispatchers.IO){
            repository.updateTask(task)
        }
    }
}