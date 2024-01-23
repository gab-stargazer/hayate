package com.lelestacia.hayate.core.common.util

import com.lelestacia.hayate.core.common.util.Util.KotlinMoshi

inline fun <reified T> toJson(data: T): String {
    val adapter = KotlinMoshi.adapter(T::class.java)
    return adapter.toJson(data)
}

inline fun <reified T> fromJson(data: String): T? {
    val adapter = KotlinMoshi.adapter(T::class.java)
    return adapter.fromJson(data)
}