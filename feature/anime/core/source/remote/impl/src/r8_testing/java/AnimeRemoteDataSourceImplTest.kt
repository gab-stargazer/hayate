package com.lelestacia.hayate.feature.anime.core.source.remote.impl_test

import androidx.paging.PagingSource
import com.lelestacia.hayate.feature.anime.core.source.remote.api.AnimeRemoteDataSourceApi
import com.lelestacia.hayate.feature.anime.core.source.remote.api.dto.anime.AnimeDto
import javax.inject.Inject

class AnimeRemoteDataSourceImplTest @Inject constructor() : AnimeRemoteDataSourceApi {

    override fun getTopAnime(
        type: String?,
        filter: String?,
        rating: String?,
        sfw: Boolean
    ): PagingSource<Int, AnimeDto> {
        return TestPagingSource()
    }

    override fun getCurrentSeasonAnime(
        filter: String?,
        sfw: Boolean
    ): PagingSource<Int, AnimeDto> {
        return TestPagingSource()
    }

    override fun getUpcomingSeasonAnime(
        filter: String?,
        sfw: Boolean
    ): PagingSource<Int, AnimeDto> {
        return TestPagingSource()
    }

    override fun getScheduledAnime(
        filter: String?,
        sfw: Boolean
    ): PagingSource<Int, AnimeDto> {
        return TestPagingSource()
    }
}