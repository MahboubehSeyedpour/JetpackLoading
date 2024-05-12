package com.spr.jetpack_loading.enums

enum class TriangleCardFace(
    val axis: RotationAxis,
    val initValue: Float,
    val targetValue: Float
) {
    AxisY(axis = RotationAxis.AxisY, initValue = 0f, targetValue = 180f) {
        override val next: TriangleCardFace
            get() = AxisX
    },
    AxisX(axis = RotationAxis.AxisX, initValue = 0f, targetValue = 180f) {
        override val next: TriangleCardFace
            get() = MAxisY
    },
    MAxisY(axis = RotationAxis.AAxisY, initValue = 180f, targetValue = 0f) {
        override val next: TriangleCardFace
            get() = MAxisX
    },
    MAxisX(axis = RotationAxis.AAxisX, initValue = 180f, targetValue = 0f) {
        override val next: TriangleCardFace
            get() = AxisY
    };

    abstract val next: TriangleCardFace
}