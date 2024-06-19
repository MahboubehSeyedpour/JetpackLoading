package com.spr.jetpack_loading.components.indicators

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke

@Composable
fun BallScaleRippleIndicator(
    color: Color = Color.White,
    minRadiusRatio: Float = 0f,
    maxRadiusRatio: Float = 2.2f,
    radius: Float = 35f,
    penThickness: Float = 5f,
    animationDuration: Int = 1100
) {

    val scale by rememberInfiniteTransition().animateFloat(
        initialValue = minRadiusRatio,
        targetValue = maxRadiusRatio,
        animationSpec = infiniteRepeatable(
            animation = tween(animationDuration, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    Box {
        Canvas(modifier = Modifier) {
            drawCircle(
                color = color,
                radius = radius * scale,
                style = Stroke(width = penThickness)
            )
        }
    }
}