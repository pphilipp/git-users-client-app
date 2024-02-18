package com.example.gitusersclient.presentation.ui.user_details_screen

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.design_system.AppToolBar
import com.example.design_system.AppToolBarUiModel
import com.example.design_system.FullScreenProgressView
import com.example.design_system.theme.X_LARGE_SPACE
import com.example.gitusersclient.R

@Composable
fun UserDetailsScreen(
    modifier: Modifier = Modifier,
    viewState: UserDetailsScreenViewState,
    onBackPressed: () -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            AppToolBar(
                useDarkTheme = isSystemInDarkTheme(),
                toolBarUiModel = AppToolBarUiModel(
                    title = viewState.userDetailsUiModel.login
                ),
                onBackPressed = onBackPressed
            )
        },
        content = {
            UserDetailsContent(
                modifier = modifier.padding(it),
                uiModel = viewState.userDetailsUiModel
            )
        }
    )

    FullScreenProgressView(isVisible = viewState.isLoading)
}

@Composable
fun UserDetailsContent(
    modifier: Modifier,
    uiModel: UserDetailsUiModel
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = X_LARGE_SPACE),
            horizontalArrangement = Arrangement.Center
        ) {
            AsyncImage(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(120.dp),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(uiModel.avatarUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
        }

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(X_LARGE_SPACE),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = Bold,
                fontSize = 24.sp
            ),
            text = uiModel.name
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = X_LARGE_SPACE),
            style = MaterialTheme.typography.bodyLarge,
            text = uiModel.company
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = X_LARGE_SPACE),
            style = MaterialTheme.typography.bodyLarge,
            text = uiModel.location
        )

        if (uiModel.bio.isNotEmpty()) {
            Card(
                modifier = Modifier
                    .padding(X_LARGE_SPACE),
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = X_LARGE_SPACE)
                        .padding(top = X_LARGE_SPACE),
                    style = MaterialTheme.typography.titleMedium,
                    text = stringResource(R.string.user_details__title_bio)
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(X_LARGE_SPACE),
                    style = MaterialTheme.typography.bodyLarge,
                    text = uiModel.bio
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserDetailsScreenPreview() {
    UserDetailsScreen(
        modifier = Modifier,
        viewState = UserDetailsScreenViewState(
            isLoading = false,
            userDetailsUiModel = UserDetailsUiModel(
                id = 1,
                login = "login",
                name = "Name Name",
                avatarUrl = "",
                company = "company",
                location = "location",
                bio = "boi boi boi boi boi boi boi boi boi boi boi boi "
            )
        ),
        onBackPressed = {}
    )
}
