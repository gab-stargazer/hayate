package com.lelestacia.hayate.core.firebase

interface UpdatePreferences {

    suspend fun updateFeature(value: Map<String, Boolean>)
}