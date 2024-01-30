package dev.robert.authentication.utils

sealed class UiEvents {
    data class ErrorEvent(val message: String): UiEvents()
    data class NavigationEvent(val route: String): UiEvents()
}