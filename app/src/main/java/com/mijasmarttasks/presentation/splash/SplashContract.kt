package com.mijasmarttasks.presentation.splash

import com.mijasmarttasks.presentation.util.ViewEvent
import com.mijasmarttasks.presentation.util.ViewSideEffect
import com.mijasmarttasks.presentation.util.ViewState

class SplashContract {

    sealed class Event : ViewEvent

    class State : ViewState

    sealed class Effect : ViewSideEffect {
        data object NavigateToTasksScreen : Effect()
    }
}