package com.mijasmarttasks.data.task_details.repository

import com.mijasmarttasks.data.local.TaskDao
import com.mijasmarttasks.data.local.toTask
import com.mijasmarttasks.data.local.toTaskEntity
import com.mijasmarttasks.domain.task_details.repository.TaskDetailsRepository
import com.mijasmarttasks.domain.tasks.model.Task
import javax.inject.Inject

class TaskDetailsRepositoryImpl @Inject constructor(
    private val taskDao: TaskDao
) : TaskDetailsRepository {

    override suspend fun getTaskFromDatabase(id: String): Task = taskDao.getTaskById(id).toTask()

    override suspend fun updateTask(task: Task) = taskDao.updateTask(task.toTaskEntity())

}