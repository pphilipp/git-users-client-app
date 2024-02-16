package com.example.gitusersclient.presentation.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.gitusersclient.presentation.ui.user_details_screen.UserDetailsScreen
import com.example.gitusersclient.presentation.ui.user_details_screen.UserDetailsScreenEvent
import com.example.gitusersclient.presentation.ui.user_details_screen.UserDetailsScreenViewModel
import com.example.gitusersclient.presentation.ui.user_list_screen.UsersListScreen
import com.example.gitusersclient.presentation.ui.user_list_screen.UsersListScreenEvent
import com.example.gitusersclient.presentation.ui.user_list_screen.UsersListScreenViewModel
import org.koin.androidx.compose.getViewModel

fun NavGraphBuilder.usersListScreenRoute(
    navController: NavController
) {
    composable(route = RootGraph.UsersList.route) {
        val viewModel: UsersListScreenViewModel = getViewModel()
        val viewState by viewModel.viewState.collectAsStateWithLifecycle()

        LaunchedEffect(key1= true) {
            viewModel.handleEvent(UsersListScreenEvent.InitializeEvent)
        }

        UsersListScreen(
            viewState = viewState,
            onPullDownToRefreshClicked = {

            },
            onUserListItemClicked = {
                navController.navigate(RootGraph.UserDetails.route)
            }
        )
    }
}

fun NavGraphBuilder.usersDetailsScreenRoute(
    navController: NavController
) {
    composable(route = RootGraph.UserDetails.route) {
        val viewModel: UserDetailsScreenViewModel = getViewModel()
        val viewState by viewModel.viewState.collectAsStateWithLifecycle()

        LaunchedEffect(key1= true) {
            viewModel.handleEvent(UserDetailsScreenEvent.InitializeEvent)
        }

        UserDetailsScreen(
            viewState = viewState,
        )
    }
}