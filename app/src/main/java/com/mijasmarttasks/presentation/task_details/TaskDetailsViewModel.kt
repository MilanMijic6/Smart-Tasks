package com.mijasmarttasks.presentation.task_details

import androidx.lifecycle.viewModelScope
import com.mijasmarttasks.domain.task_details.use_case.GetTaskUseCase
import com.mijasmarttasks.domain.task_details.use_case.GetTaskWithDaysLeftUseCase
import com.mijasmarttasks.presentation.util.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskDetailsViewModel @Inject constructor(
    private val getTaskUseCase: GetTaskUseCase,
    private val getTaskWithDaysLeftUseCase: GetTaskWithDaysLeftUseCase
) : BaseViewModel<TaskDetailsContract.Event, TaskDetailsContract.State, TaskDetailsContract.Effect>() {

    override fun setInitialState(): TaskDetailsContract.State = TaskDetailsContract.State.Init()

    override fun handleEvents(event: TaskDetailsContract.Event) {
        when (event) {
            TaskDetailsContract.Event.ClickBackArrowButton -> TODO()
            TaskDetailsContract.Event.ClickNotResolveButton -> TODO()
            TaskDetailsContract.Event.ClickResolveButton -> TODO()
            is TaskDetailsContract.Event.ShowTaskDetails -> getTask(event.id)
        }
    }

    private fun getTask(id: String) {
        viewModelScope.launch {
            runCatching {
                val task = getTaskUseCase.invoke(id)
                val taskWithDayLeft = getTaskWithDaysLeftUseCase.invoke(task)
                taskWithDayLeft
            }.onSuccess { taskWithDayLeft ->
                setState {
                    TaskDetailsContract.State.Init(
                        viewState.value.taskDetailsScreenModel.copy(
                            taskWithDaysLeft = taskWithDayLeft
                        )
                    )
                }
            }.onFailure {
                setState {
                    TaskDetailsContract.State.Error(viewState.value.taskDetailsScreenModel)
                }
            }
        }
    }
}