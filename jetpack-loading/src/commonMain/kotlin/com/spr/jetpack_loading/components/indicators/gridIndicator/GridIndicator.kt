package com.spr.jetpack_loading.components.indicators.gridIndicator

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.spr.jetpack_loading.enums.GridAnimationType

@Composable
fun GridIndicator(
    color: Color = Color.White,
    ballDiameter: Float = 40f,
    verticalSpace: Float = 20f,
    horizontalSpace: Float = 20f,
    minAlpha: Float = 0.2f,
    maxAlpha: Float = 1f,
    animationDuration: Int = 600,
    animationType: GridAnimationType
) {
    when (animationType) {
        GridAnimationType.BEATING -> BallGridBeatIndicator(
            color,
            ballDiameter,
            verticalSpace,
            horizontalSpace,
            minAlpha,
            maxAlpha,
            animationDuration,
        )

        GridAnimationType.PULSATING -> GridPulsatingDot(
            color,
            ballDiameter,
            verticalSpace,
            horizontalSpace,
            minAlpha,
            maxAlpha,
            animationDuration,
        )

        GridAnimationType.DIAGONAL -> GridFadeDiagonal(
            color,
            ballDiameter,
            verticalSpace,
            horizontalSpace,
            minAlpha,
            maxAlpha,
            animationDuration,
        )

        GridAnimationType.ANTI_DIAGONAL -> GridFadeAntiDiagonal(
            color,
            ballDiameter,
            verticalSpace,
            horizontalSpace,
            minAlpha,
            maxAlpha,
            animationDuration,
        )
    }
}