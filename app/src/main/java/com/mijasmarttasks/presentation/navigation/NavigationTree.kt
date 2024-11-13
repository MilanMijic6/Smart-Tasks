package com.mijasmarttasks.presentation.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mijasmarttasks.presentation.splash.SplashScreen
import com.mijasmarttasks.presentation.task_details.TaskDetailsScreen
import com.mijasmarttasks.presentation.tasks.TasksScreen

@Composable
fun NavigationTree(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route
    ) {
        composable(
            route = Screen.SplashScreen.route
        ) {
            EnterAnimationDetail {
                SplashScreen(
                    navController = navController
                )
            }
        }
        composable(
            route = Screen.TasksScreen.route
        ) {
            EnterAnimationDetail {
                TasksScreen(
                    navController = navController
                )
            }
        }
        composable(
            route = Screen.TaskDetailsScreen.route + "/{taskId}",
        ) { backStackEntry ->
            val taskId = backStackEntry.arguments?.getString("taskId") ?: ""
            EnterAnimationDetail {
                TaskDetailsScreen(
                    navController = navController,
                    taskId = taskId
                )
            }
        }
    }
}

@Composable
fun EnterAnimationDetail(content: @Composable () -> Unit) {
    AnimatedVisibility(
        visibleState = MutableTransitionState(
            initialState = false
        ).apply { targetState = true },
        modifier = Modifier,
        enter = slideInHorizontally(),
        exit = slideOutHorizontally()
    ) {
        content()
    }
}