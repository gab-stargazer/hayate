package com.lelestacia.hayate.common.firebase

interface UpdatePreferences {

    suspend fun updateFeature(value: Map<String, Boolean>)
}