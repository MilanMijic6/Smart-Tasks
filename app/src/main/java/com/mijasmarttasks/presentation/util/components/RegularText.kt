package com.mijasmarttasks.presentation.util.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.mijasmarttasks.presentation.ui.theme.extraRegularFontFamily

@Composable
fun RegularText(
    text: String,
    textAlign: TextAlign = TextAlign.Start,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        color = Color.Black,
        fontFamily = extraRegularFontFamily,
        fontSize = 10.sp,
        textAlign = textAlign,
        modifier = modifier
    )
}