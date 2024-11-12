package com.mijasmarttasks.domain.splash.use_case

import com.mijasmarttasks.domain.tasks.model.Task
import com.mijasmarttasks.domain.tasks.repository.TasksRepository
import javax.inject.Inject

class GetAllTaskUseCase @Inject constructor(
    private val repository: TasksRepository
) {
    suspend operator fun invoke(): List<Task> = repository.fetchTasksFromApi()
}