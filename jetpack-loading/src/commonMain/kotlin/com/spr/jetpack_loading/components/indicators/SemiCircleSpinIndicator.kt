package com.spr.jetpack_loading.components.indicators

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun SemiCircleSpinIndicator(
    color: Color = Color.White,
    startAngle: Float = -90f,
    endAngle: Float = 270f,
    sweepAngle: Float = 180f,
    animationDuration: Int = 600,
    canvasSize: Dp = 40.dp,
) {

    val rotation by rememberInfiniteTransition().animateFloat(
        initialValue = startAngle,
        targetValue = endAngle,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = animationDuration, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    Canvas(modifier = Modifier.size(canvasSize)) {
        drawArc(
            color = color,
            startAngle = rotation,
            sweepAngle = sweepAngle,
            topLeft = Offset(0f, 0f),
            size = Size(size.width, size.height),
            useCenter = true
        )
    }
}