package com.spr.jetpack_loading.components.indicators.lineScaleIndicator

import androidx.compose.animation.core.FastOutLinearInEasing
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
import com.spr.jetpack_loading.extension.mirror
import kotlinx.coroutines.delay

@Composable
fun LineScalePulseOutIndicator(
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

    var indexList = mutableListOf<Int>()
    for (index in 0..lineCount / 2) {
        indexList.add(index)
    }
    indexList = indexList.toList().mirror().toMutableList()


    val scales: List<Float> = indexList.map { index ->
        var scale by remember { mutableStateOf(0f) }

        LaunchedEffect(key1 = Unit) {

            delay(animationDuration / 2L * index)

            animate(
                initialValue = minScale,
                targetValue = maxScale,
                animationSpec = infiniteRepeatable(
                    animation = tween(durationMillis = animationDuration, easing = FastOutLinearInEasing),
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
            drawLine(
                color = color,
                start = Offset(index * distanceOnXAxis, -lineHeight / 2 * scales[index]),
                end = Offset(index * distanceOnXAxis, lineHeight / 2 * scales[index]),
                strokeWidth = penThickness,
                cap = lineType,
            )
        }
    }
}