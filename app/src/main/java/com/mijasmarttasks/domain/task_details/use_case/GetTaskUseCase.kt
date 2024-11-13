package com.mijasmarttasks.domain.task_details.use_case

import com.mijasmarttasks.domain.task_details.repository.TaskDetailsRepository
import com.mijasmarttasks.domain.tasks.model.Task
import javax.inject.Inject

class GetTaskUseCase @Inject constructor(
    private val repository: TaskDetailsRepository
) {
    suspend operator fun invoke(id: String): Task = repository.getTaskFromDatabase(id)
}