package com.spr.jetpack_loading.components.indicators.lineScaleIndicator

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
import androidx.compose.ui.graphics.StrokeCap
import kotlinx.coroutines.delay

@Composable
fun SimpleLineScaleIndicator(
    color: Color,
    lineCount: Int,
    distanceOnXAxis: Float,
    lineHeight: Int,
    animationDuration: Int,
    penThickness: Float,
    minScale: Float,
    maxScale: Float,
    lineType: StrokeCap
) {

    val scales: List<Float> = (0 until lineCount).map { index ->
        var scale by remember { mutableStateOf(minScale) }

        LaunchedEffect(key1 = Unit) {

            delay((animationDuration / lineCount.toLong()) * index)

            animate(
                initialValue = minScale,
                targetValue = maxScale,
                animationSpec = infiniteRepeatable(
                    animation = tween(durationMillis = animationDuration, easing = LinearEasing),
                    repeatMode = RepeatMode.Reverse,
                ),
            ) { value, _ ->
                scale = value
            }
        }
        scale
    }

    Canvas(modifier = Modifier) {
        for (index in 0 until lineCount) {

            val yOffset = lineHeight / 2 * scales[index]

            drawLine(
                color = color,
                start = Offset((index - lineCount/2) * distanceOnXAxis, -yOffset),
                end = Offset((index - lineCount/2) * distanceOnXAxis, yOffset),
                strokeWidth = penThickness,
                cap = lineType,
            )
        }
    }
}