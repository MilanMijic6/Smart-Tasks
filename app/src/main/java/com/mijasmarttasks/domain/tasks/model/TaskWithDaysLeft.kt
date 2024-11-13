package com.mijasmarttasks.domain.tasks.model

data class TaskWithDaysLeft(
    val task: Task,
    val daysLeft: Int?
)