package dev.robert.core.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.robert.core.data.PrefsHelper
import dev.robert.core.data.PrefsRepositoryImpl
import dev.robert.core.domain.PrefsRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {
    @Provides
    @Singleton
    fun provideDataStoreHelper(prefs: DataStore<Preferences>) = PrefsHelper(prefs)

    @Provides
    @Singleton
    fun providePrefsRepository(
        prefsHelper: PrefsHelper
    ) : PrefsRepository = PrefsRepositoryImpl(prefsHelper)

}