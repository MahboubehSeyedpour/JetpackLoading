package com.spr.jetpack_loading

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform