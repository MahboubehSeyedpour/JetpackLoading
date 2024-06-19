package com.spr.jetpack_loading.extension

fun <T> List<T>.mirror(): List<T> {
    val mirroredList = this.toMutableList()
    if (this.size.isOdd()) {
        val reversedPart = this.subList(0, size - 1).reversed()
        mirroredList.addAll(reversedPart)
    } else {
        val reversedPart = this.reversed()
        mirroredList.addAll(reversedPart)
    }
    return mirroredList
}