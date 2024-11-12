package com.mijasmarttasks.domain.splash.use_case

import com.mijasmarttasks.domain.tasks.model.Task
import com.mijasmarttasks.domain.tasks.repository.TasksRepository
import javax.inject.Inject

class SaveAllTaskUseCase @Inject constructor(
    private val repository: TasksRepository
) {
    suspend operator fun invoke(tasks: List<Task>) = repository.saveTasks(tasks)
}