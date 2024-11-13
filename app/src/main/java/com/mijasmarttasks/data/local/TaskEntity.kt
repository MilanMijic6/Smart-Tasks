package com.mijasmarttasks.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mijasmarttasks.domain.task_details.model.ItemStatus
import com.mijasmarttasks.domain.tasks.model.Task

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey val id: String,
    val targetDate: String,
    val dueDate: String?,
    val title: String,
    val description: String,
    val priority: Int,
    val comment: String? = null,
    val status: String = ItemStatus.Unresolved.displayName
)

fun TaskEntity.toTask(): Task {
    return Task(id, targetDate, dueDate, title, description, priority, status.toItemStatus())
}

fun Task.toTaskEntity(): TaskEntity {
    return TaskEntity(id, targetDate, dueDate, title, description, priority, status.displayName)
}

fun String.toItemStatus(): ItemStatus {
    return when (this) {
        ItemStatus.Unresolved.displayName -> ItemStatus.Unresolved
        ItemStatus.Resolved.displayName -> ItemStatus.Resolved
        ItemStatus.CantResolve.displayName -> ItemStatus.CantResolve
        else -> ItemStatus.Unresolved
    }
}

