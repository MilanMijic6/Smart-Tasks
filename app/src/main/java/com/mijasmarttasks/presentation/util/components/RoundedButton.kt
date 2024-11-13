package com.mijasmarttasks.presentation.util.components

import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mijasmarttasks.presentation.ui.theme.MainWhite
import com.mijasmarttasks.presentation.ui.theme.extraBoldFontFamily

@Composable
fun RoundedButton(
    text: String,
    backgroundColor: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        colors = androidx.compose.material3.ButtonDefaults.buttonColors(
            containerColor = backgroundColor
        ),
        shape = RoundedCornerShape(6.dp),
        modifier = modifier
            .height(48.dp)
    ) {
        Text(
            text = text,
            color = MainWhite,
            fontSize = 16.sp,
            fontFamily = extraBoldFontFamily
        )
    }
}