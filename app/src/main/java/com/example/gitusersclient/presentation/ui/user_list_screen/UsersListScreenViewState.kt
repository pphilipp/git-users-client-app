package com.example.gitusersclient.presentation.ui.user_list_screen

import com.example.gitusersclient.core.abstraction.presentation.base.ViewState

data class UsersListViewState(
    val isLoading: Boolean = false,
    val usersList: List<UserListItemUiModel>? = null
) : ViewState

data class UserListItemUiModel(
    val id: String,
    val name: String
)
