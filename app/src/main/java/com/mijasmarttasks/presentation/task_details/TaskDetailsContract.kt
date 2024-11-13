package com.mijasmarttasks.presentation.task_details

import com.mijasmarttasks.domain.tasks.model.TaskWithDaysLeft
import com.mijasmarttasks.presentation.util.ViewEvent
import com.mijasmarttasks.presentation.util.ViewSideEffect
import com.mijasmarttasks.presentation.util.ViewState

class TaskDetailsContract {

    sealed class Event : ViewEvent {
        data class ShowTaskDetails(val id: String) : Event()
        data class ClickResolveButton(val task: TaskWithDaysLeft) : Event()
        data class ClickNotResolveButton(val task: TaskWithDaysLeft) : Event()
        data object ClickBackArrowButton: Event()
        data class EnterCommentUpdate(val comment: String) : Event()
        data class SaveCommentButton(val comment: String, val task: TaskWithDaysLeft): Event()
    }

    sealed class State : ViewState {
        abstract val taskDetailsScreenModel: TaskDetailsScreenModel

        data class Init(
            override val taskDetailsScreenModel: TaskDetailsScreenModel = setInitState()
        ) : State()

        data class Loading(
            override val taskDetailsScreenModel: TaskDetailsScreenModel
        ) : State()

        data class Update(
            override val taskDetailsScreenModel: TaskDetailsScreenModel
        ) : State()

        data class Error(
            override val taskDetailsScreenModel: TaskDetailsScreenModel
        ) : State()
    }

    sealed class Effect : ViewSideEffect {
        data object NavigateBack : Effect()
    }
}