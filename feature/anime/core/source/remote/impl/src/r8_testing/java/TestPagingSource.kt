package com.lelestacia.hayate.feature.anime.core.source.remote.impl_test

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.lelestacia.hayate.feature.anime.core.source.remote.api.dto.anime.AnimeDto

class TestPagingSource : PagingSource<Int, AnimeDto>() {

    override fun getRefreshKey(state: PagingState<Int, AnimeDto>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AnimeDto> {
        return LoadResult.Page(
            data = sampleData,
            nextKey = null,
            prevKey = null
        )
    }
}