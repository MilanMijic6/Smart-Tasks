package com.mijasmarttasks.presentation.task_details

import com.mijasmarttasks.domain.task_details.model.ItemStatus
import com.mijasmarttasks.domain.tasks.model.Task
import com.mijasmarttasks.domain.tasks.model.TaskWithDaysLeft

data class TaskDetailsScreenModel(
    val taskWithDaysLeft: TaskWithDaysLeft,
    val errorMsg: String?
)

fun setInitState(): TaskDetailsScreenModel = TaskDetailsScreenModel(
    taskWithDaysLeft = TaskWithDaysLeft(
        task = Task(
            id = "",
            targetDate = "",
            dueDate = "",
            title = "",
            description = "",
            priority = 0,
            status = ItemStatus.Unresolved
        ),
        daysLeft = 0
    ),
    errorMsg = ""
)