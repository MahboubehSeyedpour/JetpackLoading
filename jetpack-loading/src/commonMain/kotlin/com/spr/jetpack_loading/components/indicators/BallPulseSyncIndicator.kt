package com.spr.jetpack_loading.components.indicators

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
fun BallPulseSyncIndicator(
    color: Color = Color.White,
    delay: Long = 90L,
    spaceBetweenBalls: Float = 20f,
    ballDiameter: Float = 40f,
    animationDuration: Int = 350
) {

    val ballCount = 3

    val positions = (1..ballCount).map { index ->

        var animatedValue: Float by remember { mutableStateOf(0f) }

        LaunchedEffect(key1 = Unit) {
            delay(delay + delay * index)

            animate(
                initialValue = 0f, targetValue = 50f, animationSpec = infiniteRepeatable(
                    animation = tween(durationMillis = animationDuration),
                    repeatMode = RepeatMode.Reverse,
                )
            ) { value, _ -> animatedValue = value }
        }

        animatedValue
    }

    Canvas(modifier = Modifier) {

        val center = Offset(0f, 0f)
        val xOffset = ballDiameter + spaceBetweenBalls

        for (index in 0 until ballCount) {
            drawCircle(
                color = color, radius = ballDiameter / 2, center = Offset(
                    x = when {
                        index < ballCount / 2 -> -(center.x + xOffset)
                        index == ballCount / 2 -> center.x
                        else -> center.x + xOffset
                    },
                    y = center.y - positions[index]
                )
            )
        }
    }
}
