package com.lelestacia.hayate.core.common.util

fun String?.isNotNullOrEmpty(): Boolean {
    return !this.isNullOrBlank()
}