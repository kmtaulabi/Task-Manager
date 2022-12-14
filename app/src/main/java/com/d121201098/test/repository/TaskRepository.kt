package com.d121201098.test.repository

import androidx.lifecycle.LiveData
import com.d121201098.test.data.TaskDao
import com.d121201098.test.model.Task

class TaskRepository (private val taskDao: TaskDao){
    val readAllData:LiveData<List<Task>> = taskDao.readAllData()
    val readAllDataHistory:LiveData<List<Task>> = taskDao.readAllDataHistory()

    suspend fun addTask(task: Task){
        taskDao.addTask(task)
    }
    suspend fun updateTask(task: Task){
        taskDao.updateTask(task)
    }
}