package com.spr.jetpack_loading.components.indicators

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.spr.jetpack_loading.enums.DrawStyleType
import com.spr.jetpack_loading.enums.RotationAxis
import com.spr.jetpack_loading.enums.SquareCardFace
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SquareSpinIndicator(
    color: Color = Color.White,
    animationDelay: Int = 800,
    canvasSize: Float = 60f,
    style: DrawStyleType = DrawStyleType.FILL,
    penThickness: Dp = 2.dp,
) {

    var squareCardFace by remember { mutableStateOf(SquareCardFace.AxisX) }
    var axis by remember { mutableStateOf(RotationAxis.AxisX) }
    val transition = rememberInfiniteTransition()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(squareCardFace) {
        axis = squareCardFace.axis
    }

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            while (true) {
                delay(animationDelay.toLong())
                squareCardFace = squareCardFace.next
            }
        }
    }

    val rotation by transition.animateFloat(
        initialValue = 0f,
        targetValue = squareCardFace.angle,
        animationSpec = infiniteRepeatable(
            tween(
                durationMillis = animationDelay,
                easing = FastOutSlowInEasing,
            )
        )
    )

    Canvas(modifier = Modifier
        .graphicsLayer {
            if (axis == RotationAxis.AxisX) {
                rotationX = rotation
            } else {
                rotationY = rotation
            }
        }) {

        val path = Path().apply {
            addRect(
                rect = Rect(
                    left = -canvasSize,
                    top = -canvasSize,
                    right = canvasSize,
                    bottom = canvasSize
                )
            )
        }

        drawPath(
            path = path,
            color = color,
            style = when (style) {
                DrawStyleType.FILL -> Fill
                DrawStyleType.STROKE -> Stroke(
                    width = penThickness.toPx(),
                    cap = StrokeCap.Round,
                    join = StrokeJoin.Round
                )
            }
        )
    }
}