package com.mijasmarttasks.presentation.task_details

import androidx.lifecycle.viewModelScope
import com.mijasmarttasks.domain.task_details.model.ItemStatus
import com.mijasmarttasks.domain.task_details.use_case.GetTaskUseCase
import com.mijasmarttasks.domain.task_details.use_case.GetTaskWithDaysLeftUseCase
import com.mijasmarttasks.domain.task_details.use_case.UpdateTaskUseCase
import com.mijasmarttasks.domain.tasks.model.TaskWithDaysLeft
import com.mijasmarttasks.presentation.util.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskDetailsViewModel @Inject constructor(
    private val getTaskUseCase: GetTaskUseCase,
    private val getTaskWithDaysLeftUseCase: GetTaskWithDaysLeftUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase
) : BaseViewModel<TaskDetailsContract.Event, TaskDetailsContract.State, TaskDetailsContract.Effect>() {

    override fun setInitialState(): TaskDetailsContract.State = TaskDetailsContract.State.Init()

    override fun handleEvents(event: TaskDetailsContract.Event) {
        when (event) {
            TaskDetailsContract.Event.ClickBackArrowButton -> navigateBack()
            is TaskDetailsContract.Event.ClickNotResolveButton -> resolveTask(
                ItemStatus.CantResolve,
                event.task
            )

            is TaskDetailsContract.Event.ClickResolveButton -> resolveTask(
                ItemStatus.Resolved,
                event.task
            )

            is TaskDetailsContract.Event.ShowTaskDetails -> getTask(event.id)
            is TaskDetailsContract.Event.EnterCommentUpdate -> event.updateInput()
            is TaskDetailsContract.Event.SaveCommentButton -> addComment(event.comment, event.task)
        }
    }

    private fun TaskDetailsContract.Event.EnterCommentUpdate.updateInput() {
        viewModelScope.launch {
            setState {
                TaskDetailsContract.State.Update(
                    viewState.value.taskDetailsScreenModel.copy(
                        inputComment = comment
                    )
                )
            }
        }
    }

    private fun navigateBack() {
        viewModelScope.launch {
            setEffect {
                TaskDetailsContract.Effect.NavigateBack
            }
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

    private fun resolveTask(status: ItemStatus, taskWithDaysLeft: TaskWithDaysLeft) {
        viewModelScope.launch {
            runCatching {
                val updatedTask = taskWithDaysLeft.task.copy(status = status)
                val updatedTaskWithDaysLeft = taskWithDaysLeft.copy(task = updatedTask)
                updateTaskUseCase.invoke(updatedTask)
                setState {
                    TaskDetailsContract.State.Update(
                        viewState.value.taskDetailsScreenModel.copy(
                            taskWithDaysLeft = updatedTaskWithDaysLeft
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

    private fun addComment(comment: String, taskWithDaysLeft: TaskWithDaysLeft) {
        viewModelScope.launch {
            runCatching {
                val updatedTask = taskWithDaysLeft.task.copy(comment = comment)
                val updatedTaskWithDaysLeft = taskWithDaysLeft.copy(task = updatedTask)
                updateTaskUseCase.invoke(updatedTask)
                setState {
                    TaskDetailsContract.State.Init(
                        viewState.value.taskDetailsScreenModel.copy(
                            taskWithDaysLeft = updatedTaskWithDaysLeft
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