package com.mijasmarttasks.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mijasmarttasks.domain.tasks.model.Task

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey val id: String,
    val targetDate: String,
    val dueDate: String?,
    val title: String,
    val description: String,
    val priority: Int,
    val comment: String? = null
)

fun TaskEntity.toTask(): Task {
    return Task(id, targetDate, dueDate, title, description, priority)
}

fun Task.toTaskEntity(): TaskEntity {
    return TaskEntity(id, targetDate, dueDate, title, description, priority)
}

