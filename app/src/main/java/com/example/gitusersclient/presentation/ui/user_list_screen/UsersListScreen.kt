package com.example.gitusersclient.presentation.ui.user_list_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.design_system.AppToolBar
import com.example.design_system.AppToolBarUiModel
import com.example.design_system.FullScreenProgressView
import java.util.UUID

@Composable
fun UsersListScreen(
    modifier: Modifier = Modifier,
    viewState: UsersListViewState,
    onPullDownToRefreshClicked: () -> Unit,
    onUserListItemClicked: (userId: Int) -> Unit,
) {

    Scaffold(
        modifier = modifier,
        topBar = {
            AppToolBar(
                useDarkTheme = true,
                toolBarUiModel = AppToolBarUiModel(
                    title = "Git-Users client"
                )
            )
        },
        content = {
            UsersListContent(
                modifier = modifier.padding(it),
                listOfUsers = viewState.usersList,
                onUserListItemClicked = onUserListItemClicked
            )
        }
    )

    FullScreenProgressView(isVisible = viewState.isLoading)
}

@Composable
fun UsersListContent(
    modifier: Modifier,
    listOfUsers: List<UserListItemUiModel>?,
    onUserListItemClicked: (userId: Int) -> Unit,
) {
    listOfUsers?.let {
        if (listOfUsers.isEmpty()) {
            EmptyUserListContent()
        } else {
            LazyColumn(
                modifier = modifier
            ) {
                items(listOfUsers) { userItem ->
                    UserListItem(
                        userListItemUiModel = userItem,
                        onUserItemClicked = onUserListItemClicked
                    )
                }
            }
        }


    }
}

@Composable
fun EmptyUserListContent() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "No Users found")
    }
}

@Preview
@Composable
fun UsersListScreenPreview() {
    UsersListScreen(
        viewState = UsersListViewState(
            isLoading = false,
            usersList = listOf(
                UserListItemUiModel(
                    id = UUID.randomUUID().variant(),
                    name = "user first"
                ),
                UserListItemUiModel(
                    id = UUID.randomUUID().variant(),
                    name = "user second"
                )
            )
        ),
        onUserListItemClicked = { },
        onPullDownToRefreshClicked = {}

    )
}

@Preview
@Composable
fun EmptyUsersListScreenPreview() {
    UsersListScreen(
        viewState = UsersListViewState(
            isLoading = false,
            usersList = emptyList()
        ),
        onUserListItemClicked = { },
        onPullDownToRefreshClicked = {}

    )
}