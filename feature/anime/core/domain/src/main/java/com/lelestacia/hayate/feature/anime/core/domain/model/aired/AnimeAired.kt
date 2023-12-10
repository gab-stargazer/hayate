package com.lelestacia.hayate.feature.anime.core.domain.model.aired

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import kotlinx.parcelize.Parcelize

@Immutable
@Parcelize
data class AnimeAired(
    val from: String?,
    val to: String?,
    val prop: AnimeProp,
    val string: String
) : Parcelable