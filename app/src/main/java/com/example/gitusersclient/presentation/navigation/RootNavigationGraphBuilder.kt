package com.example.gitusersclient.presentation.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.gitusersclient.presentation.ui.user_details_screen.UserDetailsScreen
import com.example.gitusersclient.presentation.ui.user_details_screen.UserDetailsScreenEvent
import com.example.gitusersclient.presentation.ui.user_details_screen.UserDetailsScreenViewModel
import com.example.gitusersclient.presentation.ui.user_list_screen.UsersListScreen
import com.example.gitusersclient.presentation.ui.user_list_screen.UsersListScreenEvent
import com.example.gitusersclient.presentation.ui.user_list_screen.UsersListScreenViewModel
import org.koin.androidx.compose.getViewModel

fun NavGraphBuilder.usersListScreenRoute(
    navController: NavController,
    viewModel: UsersListScreenViewModel
) {
    composable(route = RootGraph.UsersList.route) {
        val viewState by viewModel.viewState.collectAsStateWithLifecycle()

        UsersListScreen(
            viewState = viewState,
            onLastItemListScrolled = {
                viewModel.handleEvent(UsersListScreenEvent.LastItemListScrolledEvent)
            },
            onUserListItemClicked = { userLogin ->
                navController.navigate(RootGraph.UserDetails(userLogin).route)
            },
            onConfirm = {
                viewModel.handleEvent(UsersListScreenEvent.AlertDialogEvent.ConfirmEvent)
            },
            onDismiss = {
                viewModel.handleEvent(UsersListScreenEvent.AlertDialogEvent.DismissEvent)
            }
        )
    }
}

fun NavGraphBuilder.usersDetailsScreenRoute(
    navController: NavController
) {
    composable(
        route = RootGraph.UserDetails.route_with_args,
        arguments = listOf(
            navArgument(name = RootGraph.UserDetails.argument_key_user_login) {
                type = NavType.StringType
            },
        )
    ) { navBackStackEntry ->
        val viewModel: UserDetailsScreenViewModel = getViewModel()
        val viewState by viewModel.viewState.collectAsStateWithLifecycle()

        val userLoginArg = navBackStackEntry.arguments?.getString(
            RootGraph.UserDetails.argument_key_user_login
        )

        LaunchedEffect(key1 = true) {
            userLoginArg?.let {
                viewModel.handleEvent(UserDetailsScreenEvent.InitializeEvent(userLoginArg))
            }
        }

        UserDetailsScreen(
            viewState = viewState,
            onBackPressed = {
                navController.popBackStack()
            }
        )
    }
}