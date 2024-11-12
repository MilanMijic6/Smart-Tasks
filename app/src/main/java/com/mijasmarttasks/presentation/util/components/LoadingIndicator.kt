package com.mijasmarttasks.presentation.util.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import com.mijasmarttasks.presentation.ui.theme.MainBeige

@Composable
fun LoadingIndicator(
    modifier: Modifier = Modifier,
    size: Dp,
    sweepAngle: Float = 90f,
    color: Color,
    strokeWidth: Dp,
    animDuration: Int
) {
    val transition = rememberInfiniteTransition(
        label = "InfiniteTransition"
    )

    val currentArcStartAngle by transition.animateValue(
        0,
        360,
        Int.VectorConverter,
        infiniteRepeatable(
            animation = tween(
                durationMillis = animDuration,
                easing = LinearEasing
            )
        ), label = "ValueAnimation"
    )

    val strokeStyle = with(LocalDensity.current) {
        Stroke(
            width = strokeWidth.toPx(),
            cap = StrokeCap.Square
        )
    }

    Canvas(
        modifier = modifier
            .size(size)
    ) {
        drawCircle(
            color = MainBeige,
            style = strokeStyle
        )
        drawArc(
            color,
            startAngle = currentArcStartAngle.toFloat() - 90,
            sweepAngle = sweepAngle,
            useCenter = false,
            style = strokeStyle
        )
    }
}