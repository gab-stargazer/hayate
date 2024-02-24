package com.lelestacia.hayate.core.common.util

@JvmInline
value class Source(val name: String) {
    companion object {
        val None: Source = Source("")
    }
}

@JvmInline
value class Msg(val value: String)