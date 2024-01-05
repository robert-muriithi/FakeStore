package dev.robert.user.presentation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@Composable
@Destination
@RootNavGraph(start = true)
fun ProfileScreen(
    navigator: DestinationsNavigator,
    viewModel: ProfileViewModel = hiltViewModel()
) {

}

interface ProfileScreenNavigator {
    fun openProfileScreen()

    fun openProfileMenu(input : Any)

    fun popupBackStack()
}
