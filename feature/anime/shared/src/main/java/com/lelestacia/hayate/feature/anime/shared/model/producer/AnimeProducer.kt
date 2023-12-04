package com.lelestacia.hayate.feature.anime.shared.model.producer

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import kotlinx.parcelize.Parcelize

@Immutable
@Parcelize
data class AnimeProducer(
    val malId: Int,
    val type: String,
    val name: String,
    val url: String
) : Parcelable
