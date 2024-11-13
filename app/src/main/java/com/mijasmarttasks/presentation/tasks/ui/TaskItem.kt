package com.mijasmarttasks.presentation.tasks.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.mijasmarttasks.R
import com.mijasmarttasks.domain.task_details.model.ItemStatus
import com.mijasmarttasks.domain.tasks.model.TaskWithDaysLeft
import com.mijasmarttasks.presentation.ui.theme.MainBeige
import com.mijasmarttasks.presentation.ui.theme.MainRed
import com.mijasmarttasks.presentation.util.components.BoldText
import com.mijasmarttasks.presentation.util.components.RegularText

@Composable
fun TaskItem(
    taskWithDaysLeft: TaskWithDaysLeft,
    modifier: Modifier = Modifier,
    textColor: Color = MainRed,
    showStatusIcon: Boolean = true
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                vertical = 5.dp
            ),
        shape = RoundedCornerShape(
            size = 5.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                BoldText(
                    text = taskWithDaysLeft.task.title,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = textColor,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(
                            end = 40.dp
                        )
                )
                if (showStatusIcon && taskWithDaysLeft.task.status != ItemStatus.Unresolved) {
                    val icon = if (taskWithDaysLeft.task.status == ItemStatus.CantResolve)
                        R.drawable.btn_unresolved else R.drawable.btn_resolved
                    Icon(
                        painter = painterResource(
                            id = icon
                        ),
                        contentDescription = null,
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .size(30.dp),
                        tint = Color.Unspecified
                    )
                }
            }

            Spacer(
                modifier = Modifier
                    .height(4.dp)
            )

            HorizontalDivider(
                color = MainBeige,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
            )

            Spacer(
                modifier = Modifier
                    .height(6.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                RegularText(
                    text = stringResource(R.string.due_date_label),
                    modifier = Modifier
                        .weight(1f, fill = true)
                )

                RegularText(
                    text = stringResource(R.string.days_left_label),
                    modifier = Modifier
                        .weight(1f, fill = true)
                        .alignByBaseline(),
                    textAlign = TextAlign.End
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                BoldText(
                    text = taskWithDaysLeft.task.dueDate ?: stringResource(R.string.no_date_label),
                    modifier = Modifier
                        .weight(1f, fill = true),
                    color = textColor
                )

                BoldText(
                    text = taskWithDaysLeft.daysLeft.toString(),
                    textAlign = TextAlign.End,
                    modifier = Modifier
                        .weight(1f, fill = true)
                        .alignByBaseline(),
                    color = textColor
                )
            }
        }
    }
}