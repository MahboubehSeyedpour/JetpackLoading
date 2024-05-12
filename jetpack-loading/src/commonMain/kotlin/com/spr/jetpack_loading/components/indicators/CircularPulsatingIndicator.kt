package com.spr.jetpack_loading.components.indicators

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CircularPulsatingIndicator(
    color: Color = Color.White,
    animationDuration: Int = 850,
    progress: Float = 0.8f, // must be less than 1.0
    canvasSize: Float = 80f,
    penThickness: Dp = 2.dp,
) {

    val transition = rememberInfiniteTransition()

    val scale by transition.animateFloat(
        initialValue = 0.5f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(animationDuration / 2, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    // Turning Around Animation
    val rotation by transition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(animationDuration, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    Canvas(modifier = Modifier) {

        val sweepAngle = if (progress > 1) 360f else (360f * progress)
        val topArcStartAngle = 0f - rotation // Starting angle in degrees

        val arcPath = Path().apply {
            addArc(
                oval = Rect(
                    left = -canvasSize * scale,
                    top = -canvasSize * scale,
                    right = canvasSize * scale,
                    bottom = canvasSize * scale
                ),
                startAngleDegrees = topArcStartAngle,
                sweepAngleDegrees = sweepAngle
            )
        }

        drawPath(
            path = arcPath,
            color = color,
            style = Stroke(
                width = penThickness.toPx(),
                cap = StrokeCap.Round,
                join = StrokeJoin.Round
            )
        )
    }
}