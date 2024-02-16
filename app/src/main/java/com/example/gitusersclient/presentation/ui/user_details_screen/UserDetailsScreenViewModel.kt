package com.example.gitusersclient.presentation.ui.user_details_screen

import com.example.core.abstraction.data.IRepository
import com.example.core.abstraction.presentation.base.BaseViewModel

class UserDetailsScreenViewModel(
    private val repository: IRepository
) : BaseViewModel<UserDetailsScreenEvent, UserDetailsScreenViewState>() {

    override fun handleEvent(event: UserDetailsScreenEvent) {
        when (event) {
            UserDetailsScreenEvent.InitializeEvent -> {

            }
        }
    }

    override fun getInitialViewState(): UserDetailsScreenViewState = UserDetailsScreenViewState()
}