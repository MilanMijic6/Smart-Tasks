package com.mijasmarttasks.presentation.task_details.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mijasmarttasks.domain.tasks.model.TaskWithDaysLeft
import com.mijasmarttasks.presentation.task_details.TaskDetailsContract
import com.mijasmarttasks.presentation.ui.theme.MainYellow

@Composable
fun TaskDetailsContent(
    taskWithDaysLeft: TaskWithDaysLeft,
    handleEvent: (TaskDetailsContract.Event) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = MainYellow
            )
    ) {
        HeaderDetailsView(
            handleEvent = handleEvent
        )
        TaskDetailsResolvePart(
            taskWithDaysLeft = taskWithDaysLeft,
            handleEvent = handleEvent
        )
    }
}