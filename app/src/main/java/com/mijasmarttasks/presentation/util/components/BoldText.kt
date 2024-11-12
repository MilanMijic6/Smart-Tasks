package com.mijasmarttasks.presentation.util.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.mijasmarttasks.presentation.ui.theme.MainRed
import com.mijasmarttasks.presentation.ui.theme.extraBoldFontFamily

@Composable
fun BoldText(
    text: String,
    textAlign: TextAlign = TextAlign.Start,
    maxLines: Int = 100,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        color = MainRed,
        fontFamily = extraBoldFontFamily,
        fontSize = 15.sp,
        textAlign = textAlign,
        maxLines = maxLines,
        overflow = overflow,
        modifier = modifier
    )
}