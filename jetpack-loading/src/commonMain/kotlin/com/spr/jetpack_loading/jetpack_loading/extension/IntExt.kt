package com.spr.jetpack_loading.extension

import kotlin.math.PI

internal fun Int.isEven() = this == 0 || this/2 == 0
internal fun Int.isOdd() = !this.isEven()


// From - https://stackoverflow.com/a/77516088/20243803
internal fun Double.toRadians(): Double = this / 180.0 * PI

