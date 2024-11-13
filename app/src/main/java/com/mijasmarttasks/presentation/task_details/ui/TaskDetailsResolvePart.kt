package com.mijasmarttasks.presentation.task_details.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mijasmarttasks.R
import com.mijasmarttasks.domain.task_details.model.ItemStatus
import com.mijasmarttasks.domain.tasks.model.TaskWithDaysLeft
import com.mijasmarttasks.presentation.task_details.TaskDetailsContract
import com.mijasmarttasks.presentation.tasks.ui.TaskItem
import com.mijasmarttasks.presentation.ui.theme.MainBeige
import com.mijasmarttasks.presentation.ui.theme.MainGreen
import com.mijasmarttasks.presentation.ui.theme.MainRed
import com.mijasmarttasks.presentation.ui.theme.MainWhite
import com.mijasmarttasks.presentation.ui.theme.MainYellow
import com.mijasmarttasks.presentation.ui.theme.extraBoldFontFamily
import com.mijasmarttasks.presentation.util.components.RegularText
import com.mijasmarttasks.presentation.util.components.RoundedButton

@Composable
fun TaskDetailsResolvePart(
    taskWithDaysLeft: TaskWithDaysLeft,
    handleEvent: (TaskDetailsContract.Event) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
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
                            taskWithDaysLeft = taskWithDaysLeft,
                            textColor = when (taskWithDaysLeft.task.status) {
                                ItemStatus.Resolved -> MainGreen
                                else -> MainRed
                            },
                            showStatusIcon = false
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
                            text = if (taskWithDaysLeft.task.status == ItemStatus.CantResolve)
                                stringResource(R.string.unresolved) else taskWithDaysLeft.task.status.displayName,
                            color = when (taskWithDaysLeft.task.status) {
                                ItemStatus.Resolved -> MainGreen
                                ItemStatus.CantResolve -> MainRed
                                else -> MainYellow
                            },
                            fontSize = 16.sp,
                            fontFamily = extraBoldFontFamily,
                            modifier = Modifier
                                .padding(
                                    horizontal = 8.dp
                                )
                        )
                    }
                }
            }
        }
        when (taskWithDaysLeft.task.status) {
            ItemStatus.Resolved -> {
                ResolveUpdateView(
                    taskWithDaysLeft = taskWithDaysLeft,
                    icon = R.drawable.sign_resolved
                )
            }

            ItemStatus.CantResolve -> {
                ResolveUpdateView(
                    taskWithDaysLeft = taskWithDaysLeft,
                    icon = R.drawable.unresolved_sign
                )
            }

            else -> {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = 8.dp,
                            start = 12.dp,
                            end = 12.dp
                        ),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    RoundedButton(
                        text = "Resolve",
                        backgroundColor = MainGreen,
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        handleEvent(
                            TaskDetailsContract.Event.ClickResolveButton(
                                taskWithDaysLeft
                            )
                        )
                    }
                    RoundedButton(
                        text = "Can't resolve",
                        backgroundColor = MainRed,
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        handleEvent(
                            TaskDetailsContract.Event.ClickNotResolveButton(
                                taskWithDaysLeft
                            )
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ResolveUpdateView(
    taskWithDaysLeft: TaskWithDaysLeft,
    icon: Int
) {
    Column(
        modifier = Modifier
            .padding(
                top = 16.dp
            )
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .size(150.dp)
        )

        if (taskWithDaysLeft.task.comment != null) {
            Text(
                text = "Comment: " + taskWithDaysLeft.task.comment,
                fontFamily = extraBoldFontFamily,
                fontSize = 14.sp,
                color = MainWhite
            )
        }
    }
}