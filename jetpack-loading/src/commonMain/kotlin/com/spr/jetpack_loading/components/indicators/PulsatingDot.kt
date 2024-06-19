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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.delay

@Composable
fun PulsatingDot(
    color: Color = Color.White,
    ballDiameter: Float = 40f,
    horizontalSpace: Float = 20f,
    animationDuration: Int = 600,
    minAlpha: Float = 0f,
    maxAlpha: Float = 1f
) {

    val dotsCount = 3

    val scales: List<Float> = (0 until dotsCount).map { index ->
        var scale by remember { mutableStateOf(maxAlpha) }

        LaunchedEffect(key1 = Unit) {

            delay(animationDuration / dotsCount * index.toLong())
            animate(
                initialValue = minAlpha,
                targetValue = maxAlpha,
                animationSpec = infiniteRepeatable(
                    animation = tween(
                        durationMillis = animationDuration,
                        easing = LinearEasing
                    ),
                    repeatMode = RepeatMode.Reverse,
                ),
            ) { value, _ ->
                scale = value
            }
        }
        scale
    }

    Canvas(modifier = Modifier) {
        for (index in 0 until dotsCount) {

            val xOffset = ballDiameter + horizontalSpace

            drawCircle(
                color = color,
                radius = (ballDiameter / 2) * scales[index],
                center = Offset(
                    x = when {
                        index < dotsCount / 2 -> -(center.x + xOffset)
                        index == dotsCount / 2 -> center.x
                        else -> center.x + xOffset
                    },
                    y = 0f
                )
            )
        }
    }
}