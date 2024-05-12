package com.spr.jetpack_loading.enums

enum class SquareCardFace(val angle: Float, val axis: RotationAxis) {
    AxisX(angle = 180f, axis = RotationAxis.AxisX) {
        override val next: SquareCardFace
            get() = AxisY
    },
    AxisY(angle = 180f, axis = RotationAxis.AxisY) {
        override val next: SquareCardFace
            get() = AxisX
    };

    abstract val next: SquareCardFace
}