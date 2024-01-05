package dev.robert.fakestore.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import com.ramcosta.composedestinations.spec.NavGraphSpec
import dev.robert.fakestore.navigation.NavGraphsBuilder


sealed class BottomNavItem(val label: String, val icon: ImageVector, val destination: NavGraphSpec){
    data object Home : BottomNavItem(label = "Home", icon = Icons.Default.Home, destination = NavGraphsBuilder.home)
    data object Cart : BottomNavItem(label = "Cart", icon = Icons.Default.ShoppingCart, destination = NavGraphsBuilder.cart)
    data object Profile : BottomNavItem(label = "Profile", icon = Icons.Default.AccountCircle, destination = NavGraphsBuilder.profile)
}

