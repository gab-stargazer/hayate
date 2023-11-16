package com.lelestacia.hayate.feature.anime.exploration.source.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.lelestacia.hayate.feature.anime.exploration.data.dto.anime.AnimeDto
import com.lelestacia.hayate.feature.anime.exploration.source.endpoint.ScheduleEndpoint
import timber.log.Timber

class ScheduleAnimePaging(
    private val scheduleEndpoint: ScheduleEndpoint,
    private val filter: String?,
    private val sfw: Boolean = true,
) : PagingSource<Int, AnimeDto>() {

    override fun getRefreshKey(state: PagingState<Int, AnimeDto>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AnimeDto> {
        return try {
            val pageNumber = params.key ?: 1
            val sfwQueryValue =
                when (sfw) {
                    true -> true
                    false -> null
                }

            val apiResult = scheduleEndpoint.getScheduledAnime(
                filter = filter,
                sfw = sfwQueryValue,
                page = pageNumber,
                limit = 24
            )

            Timber.d("Successfully retrieved ${apiResult.data.size} data")

            val previousKey = when (pageNumber) {
                1 -> null
                else -> pageNumber - 1
            }

            val nextKey = when {
                apiResult.data.isEmpty() -> null
                else -> pageNumber + 1
            }

            LoadResult.Page(
                data = apiResult.data,
                prevKey = previousKey,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            Timber.e(e)
            LoadResult.Error(e)
        }
    }
}