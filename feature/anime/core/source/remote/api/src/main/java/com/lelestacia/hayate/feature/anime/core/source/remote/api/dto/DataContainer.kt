package com.lelestacia.hayate.feature.anime.core.source.remote.api.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DataContainer<T>(

    @Json(name = "data")
    val data: T
)
