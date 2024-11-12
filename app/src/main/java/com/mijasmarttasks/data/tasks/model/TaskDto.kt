package com.mijasmarttasks.data.tasks.model

import com.google.gson.annotations.SerializedName
import com.mijasmarttasks.domain.tasks.model.Task

data class TasksResponse(
    @SerializedName("tasks")
    val tasks: List<TaskDto>
)

data class TaskDto(
    val id: String,
    @SerializedName("TargetDate")
    val targetDate: String,
    @SerializedName("DueDate")
    val dueDate: String?,
    @SerializedName("Title")
    val title: String,
    @SerializedName("Description")
    val description: String,
    @SerializedName("Priority")
    val priority: Int
)

fun TaskDto.toTask(): Task {
    return Task(
        id = id,
        targetDate = targetDate,
        dueDate = dueDate,
        title = title,
        description = description,
        priority = priority
    )
}
