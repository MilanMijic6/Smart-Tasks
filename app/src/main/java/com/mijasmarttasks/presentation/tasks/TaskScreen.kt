package com.mijasmarttasks.presentation.tasks

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mijasmarttasks.R
import com.mijasmarttasks.domain.tasks.model.TaskWithDaysLeft
import com.mijasmarttasks.presentation.tasks.ui.EmptyTasksList
import com.mijasmarttasks.presentation.tasks.ui.TasksList
import com.mijasmarttasks.presentation.ui.theme.MainYellow
import com.mijasmarttasks.presentation.ui.theme.extraBoldFontFamily
import com.mijasmarttasks.presentation.util.components.Loader
import com.mijasmarttasks.presentation.util.isSameDay
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@Composable
fun TasksScreen(
    navController: NavController,
    viewModel: TasksScreenViewModel = hiltViewModel()
) {
    val handleEvent: (TasksContract.Event) -> Unit = { viewModel.handleEvents(it) }
    when (val state = viewModel.viewState.value) {
        is TasksContract.State.Error -> {
            //todo handle error case
        }

        is TasksContract.State.Loading -> {
            Loader()
        }

        is TasksContract.State.Init -> {
            TasksScreenContent(
                tasks = state.tasksScreenModel.tasks,
                selectedDate = state.tasksScreenModel.selectedDate,
                handleEvent = handleEvent
            )
        }
    }
    LaunchedEffect(Unit) {
        viewModel.handleEvents(TasksContract.Event.ShowTasksForToday)
        viewModel.effect.collect { effect ->
            when (effect) {
                is TasksContract.Effect.NavigateToDetailsScreen -> {

                }
            }
        }
    }
}

@Composable
fun TasksScreenContent(
    tasks: List<TaskWithDaysLeft>,
    selectedDate: Date,
    handleEvent: (TasksContract.Event) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MainYellow)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBackIosNew,
                contentDescription = "Left Arrow",
                tint = Color.White,
                modifier = Modifier.clickable {
                    handleEvent(TasksContract.Event.ShowTasksForYesterday)
                }
            )
            Text(
                text = formatDateLabel(selectedDate),
                color = Color.White,
                fontSize = 18.sp,
                fontFamily = extraBoldFontFamily,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
            Icon(
                imageVector = Icons.Default.ArrowForwardIos,
                contentDescription = "Right Arrow",
                tint = Color.White,
                modifier = Modifier.clickable {
                    handleEvent(TasksContract.Event.ShowTasksForTomorrow)
                }
            )
        }

        if (tasks.isEmpty()) EmptyTasksList()
        else TasksList(tasks)
    }
}

@Composable
fun formatDateLabel(selectedDate: Date): String {
    val today = Calendar.getInstance()

    return if (selectedDate.isSameDay(today)) {
        stringResource(R.string.today_label)
    } else {
        val dateFormatter = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        dateFormatter.format(selectedDate)
    }
}
