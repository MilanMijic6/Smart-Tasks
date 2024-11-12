package com.mijasmarttasks.presentation.util.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mijasmarttasks.presentation.ui.theme.MainRed

@Composable
fun Loader() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        LoadingIndicator(
            size = 40.dp,
            color = MainRed,
            strokeWidth = 4.dp,
            animDuration = 500
        )
    }
}