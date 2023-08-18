package com.example.jetpackloading.ui.theme.loading_component

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.example.jetpackloading.ANIMATION_DEFAULT_COLOR
import com.example.jetpackloading.TRIANGLE_ANIMATION_DELAY
import com.example.jetpackloading.enums.RotationAxis
import com.example.jetpackloading.enums.TriangleCardFace
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.sqrt

@Composable
fun TriangleSpinIndicator(
    color: Color = ANIMATION_DEFAULT_COLOR,
    animationDelay: Int = TRIANGLE_ANIMATION_DELAY
) {

    var cardFace by remember { mutableStateOf(TriangleCardFace.AxisY) }
    val transition = rememberInfiniteTransition()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            while (true) {
                delay(animationDelay.toLong())
                cardFace = cardFace.next
            }
        }
    }

    val rotation by transition.animateFloat(
        initialValue = cardFace.initValue,
        targetValue = cardFace.targetValue,
        animationSpec = infiniteRepeatable(
            tween(
                durationMillis = animationDelay,
                easing = FastOutSlowInEasing,
            )
        )
    )

    val rX = when (cardFace.axis) {
        RotationAxis.AxisY -> 0f
        RotationAxis.AxisX -> rotation
        RotationAxis.AAxisY -> -180f // The rotation in this part takes place around the axis of the Y, and the direction is -180 relative to the axis of the X (the image is upside down).
        RotationAxis.AAxisX -> rotation
    }

    val rY = when (cardFace.axis) {
        RotationAxis.AxisY -> rotation
        RotationAxis.AxisX -> 0f
        RotationAxis.AAxisY -> rotation
        RotationAxis.AAxisX -> 0f
    }

    Canvas(
        modifier = Modifier
            .size(40.dp)
            .graphicsLayer {
                rotationX = rX
                rotationY = rY
                cameraDistance = 12f * density
            }
    ) {
        drawTriangle(color)
    }
}

private fun DrawScope.drawTriangle(color: Color) {
    val path = Path()
    val sideLength = size.width
    val height = (sideLength * sqrt(3.0) / 2).toFloat()

    path.moveTo(0f, height)
    path.lineTo(sideLength, height)
    path.lineTo(sideLength / 2, 0f)
    path.close()

    drawPath(
        path = path,
        color = color // Set the desired fill color
    )
}