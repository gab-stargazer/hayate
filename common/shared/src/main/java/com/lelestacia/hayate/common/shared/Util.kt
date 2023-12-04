package com.lelestacia.hayate.common.shared

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

object Util {

    val KotlinMoshi: Moshi = Moshi
        .Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
}