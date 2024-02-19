package com.lelestacia.hayate.core.common.util

import com.lelestacia.hayate.core.common.api.LoggerApi
import timber.log.Timber

internal class LoggerApiImpl : LoggerApi {

    override fun logDebug(source: Source, msg: Msg) {
        Timber.d(
            "[${
                if (source.name.isNotBlank()) {
                    source.name + " "
                } else ""
            }DEBUG] %s", msg.value
        )
    }

    override fun logInfo(source: Source, msg: Msg) {
        Timber.i(
            "[${
                if (source.name.isNotBlank()) {
                    source.name + " "
                } else ""
            }INFO] %s", msg.value
        )
    }

    override fun logWarning(source: Source, msg: Msg) {
        Timber.w(
            "[${
                if (source.name.isNotBlank()) {
                    source.name + " "
                } else ""
            }WARNING] %s", msg.value
        )
    }

    override fun logError(source: Source, msg: Msg) {
        Timber.e(
            "[${
                if (source.name.isNotBlank()) {
                    source.name + " "
                } else ""
            }ERROR] %s", msg.value
        )
    }
}