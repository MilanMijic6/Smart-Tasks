package com.mijasmarttasks.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey val id: String,
    val targetDate: String,
    val dueDate: String?,
    val title: String,
    val description: String,
    val priority: Int,
    val comment: String? = null //this will be saved locally only
)
