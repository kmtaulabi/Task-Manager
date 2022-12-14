package com.d121201098.test.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.d121201098.test.model.Task

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTask(task:Task)

    @Query("SELECT * FROM task WHERE status='Belum Selesai'")
    fun readAllData():LiveData<List<Task>>

    @Query("SELECT * FROM task WHERE status='Selesai'")
    fun readAllDataHistory():LiveData<List<Task>>

    @Update
    suspend fun updateTask(task: Task)
}