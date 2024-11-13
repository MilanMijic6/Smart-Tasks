package com.mijasmarttasks.presentation.task_details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mijasmarttasks.presentation.task_details.ui.TaskDetailsContent
import com.mijasmarttasks.presentation.util.components.Loader

@Composable
fun TaskDetailsScreen(
    navController: NavController,
    taskId: String,
    viewModel: TaskDetailsViewModel = hiltViewModel()
) {
    val handleEvent: (TaskDetailsContract.Event) -> Unit = { viewModel.handleEvents(it) }
    when (val state = viewModel.viewState.value) {
        is TaskDetailsContract.State.Error -> {
            //todo handle error case
        }

        is TaskDetailsContract.State.Loading -> {
            Loader()
        }

        is TaskDetailsContract.State.Init -> {
            TaskDetailsContent(
                navController = navController,
                taskWithDaysLeft = state.taskDetailsScreenModel.taskWithDaysLeft
            )
        }

        is TaskDetailsContract.State.Update -> {

        }
    }
    LaunchedEffect(Unit) {
        viewModel.handleEvents(TaskDetailsContract.Event.ShowTaskDetails(id = taskId))
    }
}