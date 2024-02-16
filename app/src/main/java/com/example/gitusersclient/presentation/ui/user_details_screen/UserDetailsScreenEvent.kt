package com.example.gitusersclient.presentation.ui.user_details_screen

import com.example.core.abstraction.presentation.base.ViewEvent

sealed class UserDetailsScreenEvent : ViewEvent {
    data object InitializeEvent : UserDetailsScreenEvent()
}