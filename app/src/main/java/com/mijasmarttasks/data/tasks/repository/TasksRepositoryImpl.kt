package com.mijasmarttasks.data.tasks.repository

import com.mijasmarttasks.data.local.TaskDao
import com.mijasmarttasks.data.local.toTask
import com.mijasmarttasks.data.local.toTaskEntity
import com.mijasmarttasks.data.tasks.model.toTask
import com.mijasmarttasks.data.tasks.remote.TasksApi
import com.mijasmarttasks.domain.tasks.model.Task
import com.mijasmarttasks.domain.tasks.repository.TasksRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class TasksRepositoryImpl @Inject constructor(
    private val api: TasksApi,
    private val taskDao: TaskDao
) : TasksRepository {

    override suspend fun fetchTasksFromApi(): List<Task> = api.getTasks().tasks.map { it.toTask() }

    override suspend fun saveTasks(tasks: List<Task>) = taskDao.insertAll(tasks.map { it.toTaskEntity() })

    override suspend fun getTasksFromDatabase(): List<Task> = taskDao.getAllTasks().map { it.toTask() }

    override suspend fun getTasksForSpecificDay(date: Date): List<Task> = withContext(Dispatchers.IO) {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val todayDate = dateFormat.format(date)

        if (taskDao.getAllTasks().isEmpty()) {
            val apiTasks = fetchTasksFromApi()
            saveTasks(apiTasks)
        }

        taskDao.getTasksForDate(todayDate).map { it.toTask() }
    }
}
