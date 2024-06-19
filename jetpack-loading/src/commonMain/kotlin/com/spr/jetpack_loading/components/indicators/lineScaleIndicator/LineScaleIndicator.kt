package com.spr.jetpack_loading.components.indicators.lineScaleIndicator

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import com.spr.jetpack_loading.enums.PunchType

@Composable
fun LineScaleIndicator(
    color: Color = Color.White,
    rectCount: Int = 5,
    distanceOnXAxis: Float = 30f,
    lineHeight: Int = 100,
    animationDuration: Int = 500,
    punchType: PunchType,
    lineType: StrokeCap = StrokeCap.Round,
    minScale: Float = 0.3f,
    maxScale: Float = 1.5f,
    penThickness: Float = 15f
) {
    when (punchType) {
        PunchType.RANDOM_PUNCH -> LineScalePartyIndicator(
            color = color,
            lineCount = rectCount,
            lineHeight = lineHeight,
            animationDuration = animationDuration,
            penThickness = penThickness,
            minScale = minScale,
            maxScale = maxScale,
            distanceOnXAxis = distanceOnXAxis,
            lineType = lineType
        )

        PunchType.ACCORDION_PUNCH -> SimpleLineScaleIndicator(
            color = color,
            lineCount = rectCount,
            lineHeight = lineHeight,
            animationDuration = 600,
            penThickness = penThickness,
            minScale = minScale,
            maxScale = maxScale,
            distanceOnXAxis = distanceOnXAxis,
            lineType = lineType
        )

        PunchType.SYMMETRIC_PUNCH -> LineScalePulseOutIndicator(
            color = color,
            lineCount = rectCount,
            lineHeight = lineHeight,
            animationDuration = animationDuration,
            penThickness = penThickness,
            minScale = minScale,
            maxScale = maxScale,
            distanceOnXAxis = distanceOnXAxis,
            lineType = lineType
        )

        PunchType.PULSE_OUT_PUNCH -> LineScalePulseOutRapidIndicator(
            color = color,
            lineCount = rectCount,
            distanceOnXAxis = distanceOnXAxis,
            lineHeight = lineHeight,
            animationDuration = animationDuration,
            penThickness = penThickness,
            minScale = minScale,
            maxScale = maxScale,
            lineType = lineType
        )
    }
}