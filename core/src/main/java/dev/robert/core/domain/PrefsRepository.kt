package dev.robert.core.domain

import kotlinx.coroutines.flow.Flow

interface PrefsRepository {
    val theme : Flow<Int>
    suspend fun setTheme(theme: Int)
}