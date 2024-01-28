package com.lelestacia.hayate.feature.anime.core.source.remote.impl.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.lelestacia.hayate.feature.anime.core.source.remote.api.dto.anime.AnimeDto
import com.lelestacia.hayate.feature.anime.core.source.remote.impl.endpoint.TopEndpoint
import timber.log.Timber

internal class TopAnimePaging(
    private val topEndpoint: TopEndpoint,
    private val type: String? = null,
    private val filter: String? = null,
    private val rating: String? = null,
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

            val apiResult = topEndpoint.getTopAnime(
                type = type,
                filter = filter,
                rating = rating,
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
            Timber.e(e.stackTraceToString())
            LoadResult.Error(e)
        }
    }
}