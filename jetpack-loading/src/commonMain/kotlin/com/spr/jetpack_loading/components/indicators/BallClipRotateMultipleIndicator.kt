package com.spr.jetpack_loading.components.indicators

import androidx.compose.animation.core.FastOutSlowInEasing
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
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun BallClipRotateMultipleIndicator(
    color: Color = Color.White,
    animationDuration: Int = 600,
    penThickness: Dp = 2.dp,
    canvasSize: Float = 80f,
) {

// ------------  Animations -----------------------
    val transition = rememberInfiniteTransition()

    val rotation by transition.animateFloat(
        initialValue = 0F,
        targetValue = 180F,
        animationSpec = infiniteRepeatable(
            animation = tween(animationDuration, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    val scale by transition.animateFloat(
        initialValue = 0.5f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(animationDuration, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )


// -------------------------  path: outer arcs  -----------------------
    val sweepAngle = 120f

    val inTopArcStartAngle = 150f - rotation // Starting angle in degrees
    val outBottomArcStartAngle = 330f - rotation // Starting angle in degrees

    val outerTopArcPath = Path().apply {
        addArc(
            oval = Rect(
                left = -canvasSize * scale,
                top = -canvasSize * scale,
                right = canvasSize * scale,
                bottom = canvasSize * scale
            ),
            startAngleDegrees = inTopArcStartAngle,
            sweepAngleDegrees = sweepAngle
        )
    }

    val outerBottomArcPath = Path().apply {
        addArc(
            oval = Rect(
                left = -canvasSize * scale,
                top = -canvasSize * scale,
                right = canvasSize * scale,
                bottom = canvasSize * scale
            ),
            startAngleDegrees = outBottomArcStartAngle,
            sweepAngleDegrees = sweepAngle
        )
    }


// -------------------------  path: inner arcs  -----------------------
    val innerTopArcStartAngle = 90f + rotation // Starting angle in degrees
    val innerBottomArcStartAngle = 270f + rotation // Starting angle in degrees

    val innerTopArcPath = Path().apply {
        addArc(
            oval = Rect(
                left = -(canvasSize / 2) * scale,
                top = -(canvasSize / 2) * scale,
                right = (canvasSize / 2) * scale,
                bottom = (canvasSize / 2) * scale
            ),
            startAngleDegrees = innerTopArcStartAngle,
            sweepAngleDegrees = sweepAngle
        )
    }

    val innerBottomArcPath = Path().apply {
        addArc(
            oval = Rect(
                left = -(canvasSize / 2) * scale,
                top = -(canvasSize / 2) * scale,
                right = (canvasSize / 2) * scale,
                bottom = (canvasSize / 2) * scale
            ),
            startAngleDegrees = innerBottomArcStartAngle,
            sweepAngleDegrees = sweepAngle
        )
    }




    Canvas(modifier = Modifier) {

        // outer top arc
        drawPath(
            path = outerTopArcPath,
            color = color,
            style = Stroke(
                width = penThickness.toPx(),
                cap = StrokeCap.Round,
                join = StrokeJoin.Round
            )
        )

        // inner top arc
        drawPath(
            path = innerTopArcPath,
            color = color,
            style = Stroke(
                width = penThickness.toPx(),
                cap = StrokeCap.Round,
                join = StrokeJoin.Round
            )
        )

        // inner bottom arc
        drawPath(
            path = innerBottomArcPath,
            color = color,
            style = Stroke(
                width = penThickness.toPx(),
                cap = StrokeCap.Round,
                join = StrokeJoin.Round
            )
        )

        // outer bottom arc
        drawPath(
            path = outerBottomArcPath,
            color = color,
            style = Stroke(
                width = penThickness.toPx(),
                cap = StrokeCap.Round,
                join = StrokeJoin.Round
            )
        )
    }
}