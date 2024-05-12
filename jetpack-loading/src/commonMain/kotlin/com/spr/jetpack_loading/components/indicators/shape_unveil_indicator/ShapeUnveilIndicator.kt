package com.spr.jetpack_loading.components.indicators.shape_unveil_indicator

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.spr.jetpack_loading.enums.ShapeType

@Composable
fun ShapeUnveilIndicator(
    modifier: Modifier = Modifier,
    shapeType: ShapeType = ShapeType.TRIANGLE,
    color: Color = Color.White,
    canvasSize: Float = 200f,
    circleDiameter: Float = canvasSize / 4,
    circleCounts: Int = 6,  // currently just available for ShapeType.CIRCLE
    animationDuration: Int = 3000
) {
    when (shapeType) {
        ShapeType.TRIANGLE -> TriangleShapeIndicator(
            modifier,
            color,
            canvasSize,
            circleDiameter,
            animationDuration
        )

        ShapeType.CIRCLE -> CircleShapeIndicator(
            modifier,
            color,
            canvasSize,
            circleDiameter,
            circleCounts,
            animationDuration
        )
    }
}