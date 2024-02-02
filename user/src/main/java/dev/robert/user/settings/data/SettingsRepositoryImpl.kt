package dev.robert.user.settings.data

import dev.robert.core.data.PrefsHelper
import dev.robert.core.domain.PrefsRepository
import kotlinx.coroutines.flow.Flow

class SettingsRepositoryImpl(
    private val prefsHelper: PrefsHelper
) : PrefsRepository {
    override val theme: Flow<Int>
        get() = prefsHelper.getTheme

    override suspend fun setTheme(theme: Int) {
        prefsHelper.saveTheme(theme)
    }

}