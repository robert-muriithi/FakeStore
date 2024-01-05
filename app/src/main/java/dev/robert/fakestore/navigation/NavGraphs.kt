package dev.robert.fakestore.navigation

import com.ramcosta.composedestinations.dynamic.routedIn
import com.ramcosta.composedestinations.spec.DestinationSpec
import com.ramcosta.composedestinations.spec.NavGraphSpec
import com.ramcosta.composedestinations.spec.Route
import dev.robert.authentication.presentation.destinations.LoginScreenDestination
import dev.robert.authentication.presentation.destinations.RegisterScreenDestination
import dev.robert.cart.presentation.destinations.CartScreenDestination
import dev.robert.products.presentation.destinations.HomeScreenDestination
import dev.robert.user.presentation.destinations.ProfileScreenDestination

object NavGraphsBuilder {
    val auth = object : NavGraphSpec {
        override val destinationsByRoute: Map<String, DestinationSpec<*>>
            get() = mapOf(
                "login_screen" to LoginScreenDestination,
                "register_screen" to RegisterScreenDestination
            )

        override val route: String
            get() = "auth"
        override val startRoute: Route
            get() = LoginScreenDestination routedIn this
    }

    val home = object : NavGraphSpec {
        override val destinationsByRoute: Map<String, DestinationSpec<*>>
            get() = mapOf(
                "home_screen" to HomeScreenDestination,
                "cart_screen" to CartScreenDestination,
                "profile_screen" to ProfileScreenDestination
            )

        override val route: String
            get() = "home_screen"
        override val startRoute: Route
            get() = HomeScreenDestination routedIn this
    }

    val cart = object : NavGraphSpec {
        override val destinationsByRoute: Map<String, DestinationSpec<*>>
            get() = mapOf(
                "cart_screen" to CartScreenDestination
            )

        override val route: String
            get() = "cart_screen"
        override val startRoute: Route
            get() = CartScreenDestination routedIn this
    }

    val profile = object : NavGraphSpec {
        override val destinationsByRoute: Map<String, DestinationSpec<*>>
            get() = mapOf(
                "profile_screen" to ProfileScreenDestination
            )

        override val route: String
            get() = "profile_screen"
        override val startRoute: Route
            get() = ProfileScreenDestination routedIn this
    }


    val root = object : NavGraphSpec {
        override val route = "root"
        override val startRoute = home
        override val destinationsByRoute = emptyMap<String, DestinationSpec<*>>()
        override val nestedNavGraphs = listOf(
            home,
            /*cart,*/
            profile,
        )
    }

    fun root(isLoggedIn: Boolean) = object : NavGraphSpec {
        override val route = "root"
        override val startRoute = if (isLoggedIn) home else auth
        override val destinationsByRoute = emptyMap<String, DestinationSpec<*>>()
        override val nestedNavGraphs = listOf(
            auth,
            home,
            /*cart,*/
            profile,
        )
    }
}