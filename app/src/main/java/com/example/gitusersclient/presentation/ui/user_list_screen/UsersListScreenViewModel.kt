package com.example.gitusersclient.presentation.ui.user_list_screen

import com.example.gitusersclient.core.abstraction.presentation.base.BaseViewModel

class UsersListScreenViewModel(

) : BaseViewModel<UsersListScreenEvent, UsersListViewState>(){
    override fun handleEvent(event: UsersListScreenEvent) {
        when(event) {
            UsersListScreenEvent.InitializeEvent -> {}
            UsersListScreenEvent.PullDownToRefreshEvent -> doOnPullDownToRefresh()
            is UsersListScreenEvent.SelectUserEvent -> doOnUserSelected()
        }
    }

    private fun doOnUserSelected() {

    }

    private fun doOnPullDownToRefresh() {

    }

    override fun getInitialViewState(): UsersListViewState = UsersListViewState()
}