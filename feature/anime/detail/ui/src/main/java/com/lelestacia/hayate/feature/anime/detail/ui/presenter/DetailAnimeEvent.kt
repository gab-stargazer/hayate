package com.lelestacia.hayate.feature.anime.detail.ui.presenter

sealed class DetailAnimeEvent {

    data class InsertWatchList(val animeID: Int) : DetailAnimeEvent()
}