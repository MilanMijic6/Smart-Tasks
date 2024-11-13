package com.mijasmarttasks.presentation.task_details.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.mijasmarttasks.R
import com.mijasmarttasks.presentation.task_details.TaskDetailsContract
import com.mijasmarttasks.presentation.ui.theme.MainGreen
import com.mijasmarttasks.presentation.ui.theme.MainRed
import com.mijasmarttasks.presentation.util.components.RoundedButton

@Composable
fun CommentDialog(
    title: String,
    onDismiss: () -> Unit,
    onConfirm: (String) -> Unit,
    handleEvent: (TaskDetailsContract.Event) -> Unit
) {
    var text by remember { mutableStateOf("") }

    Dialog(
        onDismissRequest = {
            onDismiss()
        }
    ) {
        Surface(
            shape = RoundedCornerShape(6.dp),
            color = MaterialTheme.colorScheme.surface,
            modifier = Modifier
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = title,
                    modifier = Modifier
                        .padding(
                            bottom = 16.dp
                        )
                )
                TextField(
                    value = text,
                    onValueChange = {
                        text = it
                        handleEvent(TaskDetailsContract.Event.EnterCommentUpdate(it))
                    },
                    label = {
                        Text(stringResource(R.string.enter_your_comment_label))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Spacer(
                    modifier = Modifier
                        .height(16.dp)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    RoundedButton(
                        text = stringResource(R.string.no_label),
                        backgroundColor = MainRed,
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        onDismiss()
                    }
                    RoundedButton(
                        text = stringResource(R.string.yes_label),
                        backgroundColor = MainGreen,
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        onConfirm(text)
                        onDismiss()
                    }
                }
            }
        }
    }
}