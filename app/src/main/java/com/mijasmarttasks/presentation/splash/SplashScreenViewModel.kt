package com.mijasmarttasks.presentation.splash

import androidx.lifecycle.viewModelScope
import com.mijasmarttasks.domain.splash.use_case.CheckTasksUseCase
import com.mijasmarttasks.domain.splash.use_case.GetAllTaskUseCase
import com.mijasmarttasks.domain.splash.use_case.SaveAllTaskUseCase
import com.mijasmarttasks.presentation.util.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val checkTasksUseCase: CheckTasksUseCase,
    private val getAllTaskUseCase: GetAllTaskUseCase,
    private val saveAllTaskUseCase: SaveAllTaskUseCase
): BaseViewModel<SplashContract.Event, SplashContract.State, SplashContract.Effect>() {

    init {
        checkTasks()
    }

    override fun setInitialState(): SplashContract.State = SplashContract.State()

    override fun handleEvents(event: SplashContract.Event) {
        //for this case there is no event
    }

    private fun checkTasks() {
        viewModelScope.launch {
            runCatching {
                delay(200L)
                checkTasksUseCase.invoke()
            }.onSuccess {
                when (it) {
                    true -> {
                        setEffect {
                            SplashContract.Effect.NavigateToTasksScreen
                        }
                    }
                    false -> {
                        fetchTasks()
                    }
                }
            }
        }
    }

    private fun fetchTasks() {
        viewModelScope.launch {
            runCatching {
                getAllTaskUseCase.invoke()
            }.onSuccess {
                runCatching {
                    saveAllTaskUseCase.invoke(
                        tasks = it
                    )
                }.onSuccess {
                    setEffect { SplashContract.Effect.NavigateToTasksScreen }
                }.onFailure {
                    //todo handle failure
                }
            }.onFailure {
                //todo handle failure
            }
        }
    }
}