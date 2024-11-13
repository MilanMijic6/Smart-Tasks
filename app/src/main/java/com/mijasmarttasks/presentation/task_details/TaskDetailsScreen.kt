package com.mijasmarttasks.presentation.task_details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mijasmarttasks.R
import com.mijasmarttasks.presentation.task_details.ui.CommentDialog
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
                taskWithDaysLeft = state.taskDetailsScreenModel.taskWithDaysLeft,
                handleEvent = handleEvent
            )
        }

        is TaskDetailsContract.State.Update -> {
            val shouldShowDialog = remember { mutableStateOf(true) }
            if (shouldShowDialog.value) {
                CommentDialog(
                    title = stringResource(R.string.add_a_comment_label),
                    handleEvent = handleEvent,
                    onDismiss = {
                        shouldShowDialog.value = false
                    },
                    onConfirm = {
                        handleEvent(TaskDetailsContract.Event.SaveCommentButton(
                            comment = state.taskDetailsScreenModel.inputComment,
                            task = state.taskDetailsScreenModel.taskWithDaysLeft
                        ))
                        shouldShowDialog.value = false
                    }
                )
            }
            TaskDetailsContent(
                taskWithDaysLeft = state.taskDetailsScreenModel.taskWithDaysLeft,
                handleEvent = handleEvent
            )
        }
    }
    LaunchedEffect(Unit) {
        viewModel.handleEvents(TaskDetailsContract.Event.ShowTaskDetails(id = taskId))
        viewModel.effect.collect { effect ->
            when (effect) {
                TaskDetailsContract.Effect.NavigateBack -> navController.popBackStack()
            }
        }
    }
}