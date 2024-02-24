package com.lelestacia.hayate.core.common.api

import com.lelestacia.hayate.core.common.util.Msg
import com.lelestacia.hayate.core.common.util.Source

interface LoggerApi {
    fun logDebug(source: Source = Source.None, msg: Msg)
    fun logInfo(source: Source = Source.None, msg: Msg)
    fun logWarning(source: Source = Source.None, msg: Msg)
    fun logError(source: Source = Source.None, msg: Msg)
}