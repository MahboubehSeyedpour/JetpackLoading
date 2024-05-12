package com.spr.jetpack_loading.components.indicators

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke

@Composable
fun BallScaleRippleMultipleIndicator(
    color: Color = Color.White,
    duration: Int = 1500,
    largerRadius: Float = 100f,
    circleCount: Int = 5,
    minAlpha: Float = 0.2f,
    maxAlpha: Float = 1.0f,
    minScale: Float = 0f,
    maxScale: Float = 1.0f,
    penThickness: Float = 3f,
    repeatMode: RepeatMode = RepeatMode.Restart
) {

    val alphaSteps = (maxAlpha - minAlpha) / circleCount

    val scales: List<Float> = (0 until circleCount).map { index ->
        var scale by remember { mutableStateOf(minScale) }

        LaunchedEffect(key1 = Unit) {
            animate(
                initialValue = minScale,
                targetValue = maxScale,
                animationSpec = infiniteRepeatable(
                    animation = tween(
                        durationMillis = (circleCount - index) * duration / circleCount,
                        delayMillis = duration - (circleCount - index) * duration / circleCount,
                        easing = LinearEasing
                    ),
                    repeatMode = repeatMode,
                ),
            ) { value, _ ->
                scale = value
            }
        }
        scale
    }

    Canvas(
        modifier = Modifier
    ) {
        for (index in 0 until circleCount) {

            val radius = (circleCount - index) * (largerRadius / circleCount)

            drawCircle(
                color = color,
                radius = radius * scales[index],
                style = Stroke(width = penThickness),
                alpha = maxAlpha - (index * alphaSteps)
            )
        }
    }
}