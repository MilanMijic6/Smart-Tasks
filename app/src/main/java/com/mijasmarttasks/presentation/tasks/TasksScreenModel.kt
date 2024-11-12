package com.mijasmarttasks.presentation.tasks

import com.mijasmarttasks.domain.tasks.model.TaskWithDaysLeft
import java.util.Date

data class TasksScreenModel(
    val tasks: List<TaskWithDaysLeft>,
    val errorMsg: String?,
    val selectedDate: Date,
    val id: String
)

fun setInitState(): TasksScreenModel = TasksScreenModel(
    tasks = emptyList(),
    errorMsg = "",
    selectedDate = Date(),
    id = ""
)