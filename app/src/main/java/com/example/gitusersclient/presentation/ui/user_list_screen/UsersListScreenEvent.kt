package com.example.gitusersclient.presentation.ui.user_list_screen

import com.example.gitusersclient.core.abstraction.presentation.base.ViewEvent

sealed class UsersListScreenEvent : ViewEvent {
    data object InitializeEvent : UsersListScreenEvent()
    data object PullDownToRefreshEvent : UsersListScreenEvent()

    class SelectUserEvent(val userId: String) : UsersListScreenEvent()
}