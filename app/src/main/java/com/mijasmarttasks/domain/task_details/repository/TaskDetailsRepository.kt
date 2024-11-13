package com.mijasmarttasks.domain.task_details.repository

import com.mijasmarttasks.domain.tasks.model.Task

interface TaskDetailsRepository {
    suspend fun getTaskFromDatabase(id: String): Task
    suspend fun updateTask(task: Task)
}