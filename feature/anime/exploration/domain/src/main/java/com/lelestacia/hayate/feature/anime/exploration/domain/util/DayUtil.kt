package com.lelestacia.hayate.feature.anime.exploration.domain.util

import java.time.LocalDate

fun getCurrentDay(): String {
    val date = LocalDate.now()
    return when (date.dayOfWeek.value) {
        1 -> "Monday"
        2 -> "Tuesday"
        3 -> "Wednesday"
        4 -> "Thursday"
        5 -> "Friday"
        else -> "Unknown"
    }
}