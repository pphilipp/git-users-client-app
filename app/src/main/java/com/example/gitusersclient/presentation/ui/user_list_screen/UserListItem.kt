package com.example.gitusersclient.presentation.ui.user_list_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.design_system.theme.X_SMALL_SPACE
import java.util.UUID

@Composable
fun UserListItem(
    modifier: Modifier = Modifier,
    userListItemUiModel: UserListItemUiModel,
    onUserItemClicked: (Int) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onUserItemClicked(userListItemUiModel.id)
            }
    ) {
        Text(
            modifier = Modifier.padding(top = X_SMALL_SPACE),
            text = userListItemUiModel.name,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview
@Composable
fun UserListItemPreview() {
    UserListItem(
        userListItemUiModel = UserListItemUiModel(
            id = UUID.randomUUID().variant(),
            name = "@Philippoid"
        ),
        onUserItemClicked = { }
    )
}