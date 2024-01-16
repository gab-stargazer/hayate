package com.lelestacia.hayate.feature.anime.core.source.local.impl.converter

import androidx.room.TypeConverter
import com.lelestacia.hayate.feature.anime.core.source.local.api.entity.title.AnimeTitleEntity
import com.lelestacia.hayate.common.shared.util.fromJson as MoshiFromJson
import com.lelestacia.hayate.common.shared.util.toJson as MoshiToJson

internal class AnimeTitlesConverter {

//    @TypeConverter
//    fun fromJson(json: String): List<AnimeTitleEntity> {
//        return MoshiFromJson<List<AnimeTitleEntity>>(json) ?: emptyList()
//    }
//
//    @TypeConverter
//    fun toJson(titles: List<AnimeTitleEntity>): String {
//        return MoshiToJson(titles)
//    }

    @TypeConverter
    fun fromJson(json: String): AnimeTitleEntity {
        return MoshiFromJson<AnimeTitleEntity>(json)
            ?: throw Exception("Titles Failed to be parsed")
    }

    @TypeConverter
    fun toJson(titles: AnimeTitleEntity): String {
        return MoshiToJson(titles)
    }
}