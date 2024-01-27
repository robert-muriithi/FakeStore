package dev.robert.fakestore.navigation

import androidx.navigation.NavController
import com.ramcosta.composedestinations.dynamic.within
import com.ramcosta.composedestinations.navigation.navigate
import com.ramcosta.composedestinations.spec.NavGraphSpec
import dev.robert.cart.presentation.CartScreenNavigator
import dev.robert.products.domain.model.Product
import dev.robert.products.presentation.HomeScreenNavigator
import dev.robert.products.presentation.ProductDetailsScreenNavigator
import dev.robert.products.presentation.destinations.HomeScreenDestination
import dev.robert.user.presentation.ProfileScreenNavigator

class NavigationHelper(
    private val navGraph: NavGraphSpec,
    private val navController: NavController
) : HomeScreenNavigator, ProfileScreenNavigator, CartScreenNavigator,
    ProductDetailsScreenNavigator {
    override fun openHome() {
        navController.navigate(HomeScreenDestination within navGraph)
    }

    override fun openProductDetails(product: Product) {
//        navController.navigate(ProductDetailsScreenDestination within navGraph)
    }

    override fun openProfileScreen() {}

    override fun openProfileMenu(input: Any) {}

    override fun popupBackStack() {
        navController.navigateUp()
    }

}