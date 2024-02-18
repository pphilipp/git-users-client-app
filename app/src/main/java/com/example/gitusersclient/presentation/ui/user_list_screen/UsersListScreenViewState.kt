package com.example.gitusersclient.presentation.ui.user_list_screen

import com.example.core.abstraction.presentation.base.ViewState

data class UsersListViewState(
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val errorAlertDialogUiModel: ErrorAlertDialogUiModel = ErrorAlertDialogUiModel(),
    val lastUserId: Int = 1,
    val usersList: List<UserListItemUiModel>? = null
) : ViewState

data class ErrorAlertDialogUiModel(
    val isShown: Boolean = false,
    val message: String = ""
)

data class UserListItemUiModel(
    val id: Int,
    val login: String,
    val avatarUrl: String,
    val type: String
)
