package com.example.gitusersclient.presentation.ui.user_list_screen

import com.example.core.abstraction.presentation.base.ViewState

data class UsersListViewState(
    val isLoading: Boolean = false,
    val lastUserId: Int = 1,
    val usersList: List<UserListItemUiModel>? = null
) : ViewState

data class UserListItemUiModel(
    val id: Int,
    val name: String
)
