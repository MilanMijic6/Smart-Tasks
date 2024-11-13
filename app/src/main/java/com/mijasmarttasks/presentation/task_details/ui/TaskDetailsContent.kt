package com.mijasmarttasks.presentation.task_details.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.mijasmarttasks.domain.tasks.model.TaskWithDaysLeft
import com.mijasmarttasks.presentation.ui.theme.MainYellow

@Composable
fun TaskDetailsContent(
    navController: NavController,
    taskWithDaysLeft: TaskWithDaysLeft
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = MainYellow
            )
    ) {
        HeaderDetailsView(
            navController = navController
        )
        TaskDetailsResolvePart(
            taskWithDaysLeft = taskWithDaysLeft
        )
    }
}