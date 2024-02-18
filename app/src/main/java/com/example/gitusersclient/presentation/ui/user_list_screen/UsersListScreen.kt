package com.example.gitusersclient.presentation.ui.user_list_screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.design_system.AppToolBar
import com.example.design_system.AppToolBarUiModel
import com.example.design_system.FullScreenProgressView
import com.example.design_system.SimpleAlertDialog
import com.example.design_system.theme.X_SMALL_SPACE
import java.util.UUID

@Composable
fun UsersListScreen(
    modifier: Modifier = Modifier,
    viewState: UsersListViewState,
    onLastItemListScrolled: () -> Unit,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    onUserListItemClicked: (userLogin: String) -> Unit,

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
                onUserListItemClicked = onUserListItemClicked,
                onLastItemListScrolled = onLastItemListScrolled
            )
        }
    )

    SimpleAlertDialog(
        show = viewState.errorAlertDialogUiModel.isShown,
        dialogMessage = viewState.errorAlertDialogUiModel.message,
        onDismiss = onDismiss,
        onConfirm = onConfirm
    )


    FullScreenProgressView(isVisible = viewState.isLoading)
}

@Composable
fun UsersListContent(
    modifier: Modifier,
    listOfUsers: List<UserListItemUiModel>?,
    onUserListItemClicked: (userLogin: String) -> Unit,
    onLastItemListScrolled: () -> Unit,
) {
    listOfUsers?.let {
        if (listOfUsers.isEmpty()) {
            EmptyUserListContent()
        } else {
            LazyColumn(
                modifier = modifier
            ) {
                items(
                    count = listOfUsers.size,
                    key = { listOfUsers[it].id },
                    itemContent = { userItemIndex ->
                        UserListItem(
                            userListItemUiModel = listOfUsers[userItemIndex],
                            onUserItemClicked = onUserListItemClicked
                        )
                        if (userItemIndex >= listOfUsers.lastIndex) {
                            onLastItemListScrolled.invoke()
                        }
                    })
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = X_SMALL_SPACE),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(40.dp),
                            color = MaterialTheme.colorScheme.primary
                        )
                    }

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
                    login = "user first",
                    avatarUrl = "",
                    type = ""
                ),
                UserListItemUiModel(
                    id = UUID.randomUUID().variant(),
                    login = "user second",
                    avatarUrl = "",
                    type = ""
                )
            )
        ),
        onUserListItemClicked = {},
        onLastItemListScrolled = {},
        onConfirm = {},
        onDismiss = {}

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
        onUserListItemClicked = {},
        onLastItemListScrolled = {},
        onConfirm = {},
        onDismiss = {}
    )
}