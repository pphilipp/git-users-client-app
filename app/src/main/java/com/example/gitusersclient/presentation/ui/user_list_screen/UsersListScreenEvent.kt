package com.example.gitusersclient.presentation.ui.user_list_screen

import com.example.core.abstraction.presentation.base.ViewEvent

sealed class UsersListScreenEvent : ViewEvent {
    data object InitializeEvent : UsersListScreenEvent()
    data object LastItemListScrolledEvent : UsersListScreenEvent()

    sealed class AlertDialogEvent : UsersListScreenEvent() {
        data object ConfirmEvent : AlertDialogEvent()
        data object DismissEvent : AlertDialogEvent()
    }

}