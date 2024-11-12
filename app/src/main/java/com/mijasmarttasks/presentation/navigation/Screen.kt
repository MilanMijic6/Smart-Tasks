package com.mijasmarttasks.presentation.navigation

sealed class Screen(
    val route: String
) {

    data object SplashScreen : Screen("splash_screen")
    data object TasksScreen : Screen("tasks_screen")
    data object TaskDetailsScreen : Screen("task_details_screen")

}