package dev.robert.fakestore

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.robert.core.domain.PrefsRepository
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
   prefsRepository: PrefsRepository
): ViewModel() {
    val currentTheme = prefsRepository.theme

}