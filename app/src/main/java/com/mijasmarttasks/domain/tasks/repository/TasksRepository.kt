package com.mijasmarttasks.domain.tasks.repository

import com.mijasmarttasks.domain.tasks.model.Task
import java.util.Date

interface TasksRepository {
    suspend fun getTasksFromDatabase(): List<Task>
    suspend fun getTasksForSpecificDay(date: Date): List<Task>
    suspend fun fetchTasksFromApi(): List<Task>
    suspend fun saveTasks(tasks: List<Task>)
}
