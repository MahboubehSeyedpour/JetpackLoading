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
fun BallBeatIndicator(
    color: Color = Color.White,
    ballCount: Int = 3,
    ballDiameter: Float = 40f,
    spaceBetweenBalls: Float = 20f,
    animationDuration: Int = 350,
    animationDelay: Long = 200,
    minAlpha: Float = 0.7f,
    maxAlpha: Float = 1f
) {

    val alphas: List<Float> = (0 until ballCount).map { index ->
        var animatedValue by remember { mutableStateOf(maxAlpha) }

        LaunchedEffect(key1 = Unit) {

            if (index == ballCount / 2)
                delay(animationDelay)

            animate(
                initialValue = maxAlpha,
                targetValue = minAlpha,
                animationSpec = infiniteRepeatable(
                    animation = tween(durationMillis = animationDuration),
                    repeatMode = RepeatMode.Reverse,
                ),
            ) { value, _ ->
                animatedValue = value
            }
        }
        animatedValue
    }


    Canvas(modifier = Modifier) {

        val center = Offset(0f, 0f)
        val xOffset = ballDiameter + spaceBetweenBalls
        val yOffset = center.y

        for (index in 0 until ballCount) {

            drawCircle(
                color = color,
                radius = (ballDiameter / 2) * alphas[index],
                alpha = alphas[index],
                center = Offset(
                    x = when {
                        index < ballCount / 2 -> -(center.x + xOffset)
                        index == ballCount / 2 -> center.x
                        else -> center.x + xOffset
                    },
                    y = yOffset
                )
            )
        }
    }
}