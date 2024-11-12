package com.mijasmarttasks.presentation.tasks.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mijasmarttasks.domain.tasks.model.TaskWithDaysLeft

@Composable
fun TasksList(
    tasks: List<TaskWithDaysLeft>
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(
            horizontal = 16.dp,
            vertical = 8.dp
        )
    ) {
        items(tasks) { taskWithDayLeft ->
            TaskItem(
                taskWithDaysLeft = taskWithDayLeft
            )
            Spacer(
                modifier = Modifier
                    .height(
                        height = 8.dp
                    )
            )
        }
    }
}