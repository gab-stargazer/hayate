package com.lelestacia.hayate.feature.anime.detail.ui.util

internal fun List<String>.asText(): String {
    var temporaryText = ""
    this.forEachIndexed { index, text ->
        temporaryText += text

        if (index < this.size - 1) {
            temporaryText += ", "
        }
    }
    return temporaryText
}