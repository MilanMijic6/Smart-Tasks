package com.mijasmarttasks.domain.splash.use_case

import com.mijasmarttasks.domain.tasks.repository.TasksRepository
import javax.inject.Inject

class CheckTasksUseCase @Inject constructor(
    private val repository: TasksRepository
) {
    suspend operator fun invoke(): Boolean = repository.getTasksFromDatabase().isNotEmpty()
}

