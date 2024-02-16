package com.example.gitusersclient.presentation.ui.user_list_screen

import androidx.lifecycle.viewModelScope
import com.example.core.abstraction.data.IRepository
import com.example.core.abstraction.presentation.base.BaseViewModel
import com.example.core.common.DataResult
import com.example.gitusersclient.domain.model.UserBusinessModel
import kotlinx.coroutines.launch

class UsersListScreenViewModel(
    private val repository: IRepository
) : BaseViewModel<UsersListScreenEvent, UsersListViewState>() {

    override fun handleEvent(event: UsersListScreenEvent) {
        when (event) {
            UsersListScreenEvent.InitializeEvent -> doOnInitialize()
            UsersListScreenEvent.PullDownToRefreshEvent -> doOnPullDownToRefresh()
            is UsersListScreenEvent.SelectUserEvent -> doOnUserSelected()
        }
    }

    private fun doOnInitialize() {
        getUsersListByPage(1)

    }

    private fun getUsersListByPage(pageNumber: Int, isRefresh: Boolean = false) {
        viewModelScope.launch {
            showProgress()

            when (val data = repository.getUsersList(pageNumber)) {
                is DataResult.Error -> {

                }

                is DataResult.Success -> {
                    hideProgress()

                    setState {
                        copy(
                            usersList = data.data.map { userBM ->
                                UserListItemUiModel(
                                    id = (userBM as UserBusinessModel).id,
                                    login = userBM.login,
                                )
                            },
                            lastUserId = (data.data.last() as UserBusinessModel).id
                        )
                    }
                }
            }
        }
    }

    private fun doOnUserSelected() {

    }

    private fun getNextPage() {
        getUsersListByPage(viewState.value.lastUserId)
    }

    private fun doOnPullDownToRefresh() {
        getUsersListByPage(1)

    }

    private fun showProgress() {
        setState {
            copy(isLoading = true)
        }
    }

    private fun hideProgress() {
        setState {
            copy(isLoading = false)
        }
    }

    override fun getInitialViewState(): UsersListViewState = UsersListViewState()
}