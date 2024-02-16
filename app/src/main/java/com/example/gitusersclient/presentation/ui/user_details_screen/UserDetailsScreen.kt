package com.example.gitusersclient.presentation.ui.user_details_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun UserDetailsScreen(
    modifier: Modifier = Modifier,
    viewState: UserDetailsScreenViewState,
) {
    Column {
        Text(text = "Details")
    }

}