package com.mijasmarttasks.domain.tasks.use_case

import com.mijasmarttasks.domain.tasks.model.Task
import com.mijasmarttasks.domain.tasks.repository.TasksRepository
import java.util.Date
import javax.inject.Inject

class GetTasksForSpecificDayUseCase @Inject constructor(
    private val repository: TasksRepository
) {
    suspend operator fun invoke(date: Date): List<Task> {
        return repository.getTasksForSpecificDay(date)
    }
}
