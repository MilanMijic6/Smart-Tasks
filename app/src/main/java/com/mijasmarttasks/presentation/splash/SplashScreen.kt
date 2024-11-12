package com.mijasmarttasks.presentation.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mijasmarttasks.R
import com.mijasmarttasks.presentation.navigation.Screen
import com.mijasmarttasks.presentation.ui.theme.MainYellow

@Composable
fun SplashScreen(
    navController: NavController
) {
    val viewModel: SplashScreenViewModel = hiltViewModel()
    ShowSplashContent()
    LaunchedEffect(Unit) {
        viewModel.effect.collect {
            when (it) {
                SplashContract.Effect.NavigateToTasksScreen -> navController.navigate(Screen.TasksScreen.route) {
                    popUpTo(Screen.SplashScreen.route) {
                        inclusive = true
                    }
                }
            }
        }
    }
}

@Composable
private fun ShowSplashContent() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = MainYellow
            )
    ) {
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = "Top Logo Image",
            modifier = Modifier
                .align(
                    alignment = Alignment.TopCenter
                )
                .padding(
                    top = 80.dp
                )
        )

        Image(
            painter = painterResource(R.drawable.intro_illustration),
            contentDescription = "Bottom Image",
            modifier = Modifier
                .align(
                    alignment = Alignment.BottomCenter
                )
        )
    }
}
