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

    val home = object : NavGraphSpec {
        override val route = "home"

        override val startRoute = HomeScreenDestination routedIn this

        override val destinationsByRoute = listOf<DestinationSpec<*>>(
            HomeScreenDestination,
        ).routedIn(this)
            .associateBy { it.route }
    }

    val cart = object : NavGraphSpec {
        override val route = "cart"

        override val startRoute = CartScreenDestination routedIn this

        override val destinationsByRoute = listOf<DestinationSpec<*>>(
            CartScreenDestination,
        ).routedIn(this)
            .associateBy { it.route }
    }

    val profile = object : NavGraphSpec {
        override val route = "profile"

        override val startRoute = ProfileScreenDestination routedIn this

        override val destinationsByRoute = listOf<DestinationSpec<*>>(
            ProfileScreenDestination
        ).routedIn(this)
            .associateBy { it.route }
    }

    val auth = object : NavGraphSpec {
        override val route = "auth"

        override val startRoute = LoginScreenDestination routedIn this

        override val destinationsByRoute = listOf<DestinationSpec<*>>(
            LoginScreenDestination,
            RegisterScreenDestination
        ).routedIn(this)
            .associateBy { it.route }
    }


    /*val profile = object : NavGraphSpec {
        override val destinationsByRoute: Map<String, DestinationSpec<*>>
            get() = mapOf(
                "profile_screen" to ProfileScreenDestination
            )

        override val route: String
            get() = "profile_screen"
        override val startRoute: Route
            get() = ProfileScreenDestination routedIn this
    }*/


    val root = object : NavGraphSpec {
        override val route = "root"
        override val startRoute = home
        override val destinationsByRoute = emptyMap<String, DestinationSpec<*>>()
        override val nestedNavGraphs = listOf(
            home,
            cart,
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
            cart,
            profile,
        )
    }
}