package com.mijasmarttasks.domain.tasks.model

import com.mijasmarttasks.presentation.util.toDate

data class Task(
    val id: String,
    val targetDate: String,
    val dueDate: String?,
    val title: String,
    val description: String,
    val priority: Int
) {
    fun calculateDaysLeft(): Int? {
        val target = targetDate.toDate()
        val due = dueDate?.toDate()
        if (target != null && due != null) {
            val diffInMillis = due.time - target.time
            return (diffInMillis / (1000 * 60 * 60 * 24)).toInt()
        }
        return null
    }
}