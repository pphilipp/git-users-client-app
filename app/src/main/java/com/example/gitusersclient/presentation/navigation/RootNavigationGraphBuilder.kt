package com.example.gitusersclient.presentation.navigation

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.gitusersclient.presentation.ui.user_list_screen.UsersListScreen
import com.example.gitusersclient.presentation.ui.user_list_screen.UsersListScreenViewModel
import org.koin.androidx.compose.getViewModel

fun NavGraphBuilder.usersListScreenRoute(
    navController: NavController
) {
    composable(route = RootGraph.UsersList.route) {
        val viewModel: UsersListScreenViewModel = getViewModel()
        val viewState by viewModel.viewState.collectAsStateWithLifecycle()

        UsersListScreen(
            viewState = viewState,
            onPullDownToRefreshClicked = {

            },
            onNavigateToUserDetails = {
                navController.navigate(RootGraph.UserDetails.route)
            }
        )
    }

}