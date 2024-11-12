package com.mijasmarttasks.domain.tasks.use_case

import com.mijasmarttasks.domain.tasks.model.Task
import com.mijasmarttasks.domain.tasks.model.TaskWithDaysLeft
import com.mijasmarttasks.presentation.util.toDate
import javax.inject.Inject

class GetTasksWithDaysLeftUseCase @Inject constructor() {
    fun invoke(tasks: List<Task>): List<TaskWithDaysLeft> {
        return tasks
            .sortedWith(
                compareByDescending<Task> { it.priority }
                    .thenBy { it.dueDate?.toDate() }
            )
            .map { task ->
                val daysLeft = task.calculateDaysLeft()
                TaskWithDaysLeft(task, daysLeft)
            }
    }
}