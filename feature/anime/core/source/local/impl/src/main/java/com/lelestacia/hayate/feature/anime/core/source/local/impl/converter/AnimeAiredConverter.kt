package com.lelestacia.hayate.feature.anime.core.source.local.impl.converter

import android.net.Uri
import androidx.room.TypeConverter
import com.lelestacia.hayate.feature.anime.core.source.local.api.entity.aired.AnimeAiredEntity
import com.lelestacia.hayate.common.shared.util.fromJson as MoshiFromJson
import com.lelestacia.hayate.common.shared.util.toJson as MoshiToJson

internal class AnimeAiredConverter {

    @TypeConverter
    fun fromJson(json: String): AnimeAiredEntity {
        return MoshiFromJson<AnimeAiredEntity>(Uri.decode(json)) as AnimeAiredEntity
    }

    @TypeConverter
    fun toJson(aired: AnimeAiredEntity): String {
        return Uri.encode(MoshiToJson(aired))
    }
}