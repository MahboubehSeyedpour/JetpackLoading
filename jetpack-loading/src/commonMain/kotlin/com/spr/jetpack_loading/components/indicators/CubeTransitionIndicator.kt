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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun CubeTransitionIndicator(
    color: Color = Color.White,
    movingBalls: Int = 2,
    diameter: Float = 20f,
    spacing: Float = 60f,
    duration: Int = 1000
) {

    val transition = rememberInfiniteTransition()

    val rotation by transition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(duration, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    val scale by transition.animateFloat(
        initialValue = 0.4f,
        targetValue = 2.0f,
        animationSpec = infiniteRepeatable(
            animation = tween(duration / 2, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Canvas(modifier = Modifier) {

        val center = Offset(x = size.width / 2, y = size.height / 2)
        val angleStep = 360f / movingBalls

        // The surrounding circles are spinning
        for (index in 0 until movingBalls) {

            val angle = index * angleStep
            drawRect(
                color = color,
                size = Size(width = diameter * scale, height = diameter * scale),
                topLeft = Offset(
                    x = center.x + ((spacing) * cos(Math.toRadians((angle) + rotation.toDouble())).toFloat()),
                    y = center.y + ((spacing) * sin(Math.toRadians((angle) + rotation.toDouble())).toFloat()),
                )
            )
        }
    }
}