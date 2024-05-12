package com.spr.jetpack_loading.extension

fun Int.isEven() = this == 0 || this/2 == 0
fun Int.isOdd() = !this.isEven()