package com.mijasmarttasks.presentation.tasks

import com.mijasmarttasks.presentation.util.ViewEvent
import com.mijasmarttasks.presentation.util.ViewSideEffect
import com.mijasmarttasks.presentation.util.ViewState

class TasksContract {

    sealed class Event : ViewEvent {
        data object ShowTasksForToday : Event()
        data object ShowTasksForYesterday : Event()
        data object ShowTasksForTomorrow : Event()
        data class ClickOnTask(val taskId: String): Event()
    }

    sealed class State : ViewState {
        abstract val tasksScreenModel: TasksScreenModel

        data class Init(
            override val tasksScreenModel: TasksScreenModel = setInitState(),
        ) : State()

        data class Loading(
            override val tasksScreenModel: TasksScreenModel,
        ) : State()

        data class Error(
            override val tasksScreenModel: TasksScreenModel,
        ) : State()
    }

    sealed class Effect : ViewSideEffect {
        data class NavigateToDetailsScreen(val taskId: String) : Effect()
    }
}