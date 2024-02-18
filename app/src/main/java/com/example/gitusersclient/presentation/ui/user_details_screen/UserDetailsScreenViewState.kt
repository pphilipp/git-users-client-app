package com.example.gitusersclient.presentation.ui.user_details_screen

import com.example.core.abstraction.presentation.base.ViewState

data class UserDetailsScreenViewState(
    val isLoading: Boolean = false,
    val userDetailsUiModel: UserDetailsUiModel = UserDetailsUiModel()
) : ViewState

data class UserDetailsUiModel(
    val id: Int = -1,
    val login: String = "",
    val name: String = "",
    val avatarUrl: String = "",
    val company: String = "",
    val location: String = "",
    val bio: String = "",
)
