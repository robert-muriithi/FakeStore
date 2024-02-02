package dev.robert.core.data

import dev.robert.core.domain.PrefsRepository
import kotlinx.coroutines.flow.Flow

class PrefsRepositoryImpl(
    private val prefsHelper: PrefsHelper
) : PrefsRepository {
    override val theme: Flow<Int>
        get() = prefsHelper.getTheme

    override suspend fun setTheme(theme: Int) {
        prefsHelper.saveTheme(theme)
    }
}