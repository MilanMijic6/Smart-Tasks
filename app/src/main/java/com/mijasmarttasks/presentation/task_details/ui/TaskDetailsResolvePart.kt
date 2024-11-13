package com.mijasmarttasks.presentation.task_details.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mijasmarttasks.R
import com.mijasmarttasks.domain.tasks.model.TaskWithDaysLeft
import com.mijasmarttasks.presentation.tasks.ui.TaskItem
import com.mijasmarttasks.presentation.ui.theme.MainBeige
import com.mijasmarttasks.presentation.ui.theme.MainGreen
import com.mijasmarttasks.presentation.ui.theme.MainRed
import com.mijasmarttasks.presentation.ui.theme.MainYellow
import com.mijasmarttasks.presentation.ui.theme.extraBoldFontFamily
import com.mijasmarttasks.presentation.util.components.RegularText
import com.mijasmarttasks.presentation.util.components.RoundedButton

@Composable
fun TaskDetailsResolvePart(
    taskWithDaysLeft: TaskWithDaysLeft
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.task_details),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 12.dp
                )
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = 12.dp,
                    end = 12.dp,
                    top = 36.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .background(
                            color = Color.White
                        )
                ) {
                    TaskItem(
                        taskWithDaysLeft = taskWithDaysLeft
                    )
                    RegularText(
                        text = taskWithDaysLeft.task.description,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = 8.dp
                            )
                    )
                    Spacer(
                        modifier = Modifier
                            .height(6.dp)
                    )
                    HorizontalDivider(
                        color = MainBeige,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .padding(
                                horizontal = 8.dp
                            )
                    )
                    Spacer(
                        modifier = Modifier
                            .height(10.dp)
                    )
                    Text(
                        text = taskWithDaysLeft.task.status.displayName,
                        color = MainYellow,
                        fontSize = 16.sp,
                        fontFamily = extraBoldFontFamily,
                        modifier = Modifier
                            .padding(
                                horizontal = 8.dp
                            )
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 32.dp,
                        bottom = 16.dp
                    ),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                RoundedButton(
                    text = "Resolve",
                    backgroundColor = MainGreen,
                    modifier = Modifier
                        .weight(1f)
                ) {

                }
                RoundedButton(
                    text = "Can't resolve",
                    backgroundColor = MainRed,
                    modifier = Modifier
                        .weight(1f)
                ) {

                }
            }
        }
    }
}