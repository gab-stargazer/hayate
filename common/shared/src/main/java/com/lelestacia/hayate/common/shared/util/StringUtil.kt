package com.lelestacia.hayate.common.shared.util

fun String?.isNotNullOrEmpty(): Boolean {
    return !this.isNullOrBlank()
}