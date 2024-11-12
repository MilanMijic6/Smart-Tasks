package com.mijasmarttasks.presentation.tasks

import androidx.lifecycle.viewModelScope
import com.mijasmarttasks.domain.tasks.use_case.GetTasksForSpecificDayUseCase
import com.mijasmarttasks.domain.tasks.use_case.GetTasksWithDaysLeftUseCase
import com.mijasmarttasks.presentation.util.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class TasksScreenViewModel @Inject constructor(
    private val getTasksForSpecificDayUseCase: GetTasksForSpecificDayUseCase,
    private val getTasksWithDaysLeftUseCase: GetTasksWithDaysLeftUseCase
) : BaseViewModel<TasksContract.Event, TasksContract.State, TasksContract.Effect>() {

    private var currentDate: Calendar = Calendar.getInstance()

    override fun setInitialState(): TasksContract.State = TasksContract.State.Init()

    override fun handleEvents(event: TasksContract.Event) {
        when (event) {
            is TasksContract.Event.ClickOnTask -> event.passTaskId()
            TasksContract.Event.ShowTasksForToday -> fetchTasksForToday()
            TasksContract.Event.ShowTasksForTomorrow -> fetchTasksForTomorrow()
            TasksContract.Event.ShowTasksForYesterday -> fetchTasksForYesterday()
        }
    }

    private fun getTask(date: Date) {
        viewModelScope.launch {
            runCatching {
                val tasks = getTasksForSpecificDayUseCase.invoke(date)
                val enrichedTasks = getTasksWithDaysLeftUseCase.invoke(tasks)
                enrichedTasks
            }.onSuccess { enrichedTasks ->
                setState {
                    TasksContract.State.Init(
                        viewState.value.tasksScreenModel.copy(
                            tasks = enrichedTasks,
                            selectedDate = date
                        )
                    )
                }
            }.onFailure {
                setState {
                    TasksContract.State.Error(viewState.value.tasksScreenModel)
                }
            }
        }
    }

    private fun TasksContract.Event.ClickOnTask.passTaskId() {
        viewModelScope.launch {
            setEffect {
                TasksContract.Effect.NavigateToDetailsScreen(taskId)
            }
        }
    }

    private fun fetchTasksForToday() {
        currentDate = Calendar.getInstance()
        getTask(currentDate.time)
    }

    private fun fetchTasksForYesterday() {
        currentDate.add(Calendar.DAY_OF_YEAR, -1)
        getTask(currentDate.time)
    }

    private fun fetchTasksForTomorrow() {
        currentDate.add(Calendar.DAY_OF_YEAR, 1)
        getTask(currentDate.time)
    }
}
