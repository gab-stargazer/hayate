package com.lelestacia.hayate.feature.anime.detail.ui.util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

internal fun String.asFormattedDate(): String {
    val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX")
    val dateTime: LocalDateTime = LocalDateTime.parse(this, formatter)
    return dateTime.format(DateTimeFormatter.ofPattern("dd MMMM yyyy"))
}