package com.lelestacia.hayate.feature.anime.core.source.remote.impl.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.lelestacia.hayate.feature.anime.core.source.remote.api.dto.anime.AnimeDto
import com.lelestacia.hayate.feature.anime.core.source.remote.impl.endpoint.SearchEndpoint
import timber.log.Timber

class SearchAnimePaging(
    private val endpoint: SearchEndpoint,
    private val query: String,
    private val type: String?,
    private val rating: String?,
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

            val apiResult = endpoint.getAnimeSearch(
                query = query,
                type = type,
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