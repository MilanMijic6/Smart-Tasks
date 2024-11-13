package com.mijasmarttasks.presentation.tasks.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mijasmarttasks.domain.tasks.model.Task
import com.mijasmarttasks.domain.tasks.model.TaskWithDaysLeft

@Composable
fun TasksList(
    tasks: List<TaskWithDaysLeft>,
    onItemClicked: (Task) -> Unit
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
                taskWithDaysLeft = taskWithDayLeft,
                modifier = Modifier
                    .clickable {
                        onItemClicked(taskWithDayLeft.task)
                    }
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