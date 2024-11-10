package com.mijasmarttasks.data.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks WHERE targetDate = :date")
    fun getTasksForDate(date: String): LiveData<List<TaskEntity>>

    @Query("SELECT * FROM tasks WHERE id = :taskId")
    suspend fun getTaskById(taskId: String): TaskEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(tasks: List<TaskEntity>)

    @Update
    suspend fun updateTask(task: TaskEntity)

    @Query("DELETE FROM tasks")
    suspend fun clearAll()
}
