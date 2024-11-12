package com.mijasmarttasks.presentation.tasks.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mijasmarttasks.R
import com.mijasmarttasks.presentation.ui.theme.extraBoldFontFamily

@Composable
fun EmptyTasksList() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.empty_screen),
            contentDescription = "No Tasks",
            modifier = Modifier
                .size(250.dp)
        )
        Spacer(
            modifier = Modifier
                .height(30.dp)
        )
        Text(
            text = stringResource(R.string.no_tasks_for_today_label),
            fontSize = 24.sp,
            fontFamily = extraBoldFontFamily,
            color = Color.White
        )
    }
}