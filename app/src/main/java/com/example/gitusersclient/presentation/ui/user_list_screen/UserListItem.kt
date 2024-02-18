package com.example.gitusersclient.presentation.ui.user_list_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.design_system.theme.MEDIUM_SPACE
import com.example.design_system.theme.X_SMALL_SPACE
import java.util.UUID

@Composable
fun UserListItem(
    modifier: Modifier = Modifier,
    userListItemUiModel: UserListItemUiModel,
    onUserItemClicked: (String) -> Unit
) {
    ElevatedCard(
        modifier = modifier
            .padding(X_SMALL_SPACE)
            .padding(horizontal = MEDIUM_SPACE)
            .fillMaxWidth()
            .clickable {
                onUserItemClicked(userListItemUiModel.login)
            }

    ) {
        Row(
            modifier = Modifier
                .padding(X_SMALL_SPACE)
                .fillMaxWidth()
        ) {
            AsyncImage(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(40.dp),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(userListItemUiModel.avatarUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
            Column {
                Text(
                    modifier = Modifier
                        .padding(horizontal = X_SMALL_SPACE)
                        .fillMaxWidth(),
                    text = userListItemUiModel.login,
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    modifier = Modifier
                        .padding(horizontal = X_SMALL_SPACE)
                        .fillMaxWidth(),
                    text = "Type -  ${userListItemUiModel.type}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserListItemPreview() {
    UserListItem(
        userListItemUiModel = UserListItemUiModel(
            id = UUID.randomUUID().variant(),
            login = "@Philippoid",
            avatarUrl = "",
            type = "User"
        ),
        onUserItemClicked = { }
    )
}