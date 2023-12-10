package com.lelestacia.hayate.common.shared.util

import com.lelestacia.hayate.common.shared.util.Util.KotlinMoshi

inline fun <reified T> toJson(data: T): String {
    val adapter = KotlinMoshi.adapter(T::class.java)
    return adapter.toJson(data)
}

inline fun <reified T> fromJson(data: String): T? {
    val adapter = KotlinMoshi.adapter(T::class.java)
    return adapter.fromJson(data)
}