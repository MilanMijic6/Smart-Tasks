package com.mijasmarttasks.domain.task_details.use_case

import com.mijasmarttasks.domain.tasks.model.Task
import com.mijasmarttasks.domain.tasks.model.TaskWithDaysLeft
import javax.inject.Inject

class GetTaskWithDaysLeftUseCase @Inject constructor() {

    fun invoke(task: Task): TaskWithDaysLeft {
        val daysLeft = task.calculateDaysLeft()
        return TaskWithDaysLeft(task, daysLeft)
    }
}