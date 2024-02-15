package com.example.gitusersclient.presentation.ui.user_list_screen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.gitusersclient.design_system.AppFullScreenProgress

@Composable
fun UsersListScreen(
    modifier: Modifier = Modifier,
    viewState: UsersListViewState,
    onPullDownToRefreshClicked: () -> Unit,
    onNavigateToUserDetails: (userId: String) -> Unit,
) {

    Scaffold(
        modifier = modifier,
        content = {
            BasicText(
                modifier = Modifier.padding(it),
                text = "UsersListScreen",
            )
        }
    )
    AppFullScreenProgress(isVisible = viewState.isLoading)

}