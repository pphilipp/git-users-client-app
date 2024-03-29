package com.example.gitusersclient.presentation.ui.user_list_screen

import androidx.lifecycle.viewModelScope
import com.example.core.abstraction.data.IRepository
import com.example.core.abstraction.domain.IBusinessModel
import com.example.core.abstraction.presentation.base.BaseViewModel
import com.example.core.common.DataResult
import com.example.gitusersclient.domain.model.UserBM
import kotlinx.coroutines.launch

class UsersListScreenViewModel(
    private val repository: IRepository
) : BaseViewModel<UsersListScreenEvent, UsersListViewState>() {

    init {
        doOnInitialize()
    }

    override fun handleEvent(event: UsersListScreenEvent) {
        when (event) {
            UsersListScreenEvent.InitializeEvent -> doOnInitialize()
            UsersListScreenEvent.LastItemListScrolledEvent -> doOnLastItemScrolled()
            UsersListScreenEvent.AlertDialogEvent.ConfirmEvent -> onDialogConfirm()
            UsersListScreenEvent.AlertDialogEvent.DismissEvent -> onDialogDismiss()
        }
    }

    private fun doOnInitialize() {
        showProgress()
        getUsersListByPage(1)
    }

    private fun getUsersListByPage(pageNumber: Int) {
        viewModelScope.launch {

            when (val data = repository.getUsersList(pageNumber)) {
                is DataResult.Error -> {
                    hideProgress()

                    data.errorBody.message?.let {
                        onOpenAlertDialog(it)
                    }
                }

                is DataResult.Success -> {
                    hideProgress()

                    setState {
                        copy(
                            usersList = mergeUsersListWithActual(data.data),
                            lastUserId = (data.data.last() as UserBM).id
                        )
                    }
                }
            }
        }
    }

    private fun mergeUsersListWithActual(
        newList: List<IBusinessModel>
    ): List<UserListItemUiModel> {
        val newListUiModel = newList.map { userBM ->
            UserListItemUiModel(
                id = (userBM as UserBM).id,
                login = userBM.login,
                avatarUrl = userBM.avatarUrl,
                type = userBM.type
            )
        }
        var actualList = viewState.value.usersList?.toMutableList()

        if (actualList.isNullOrEmpty()) {
            actualList = mutableListOf()
            actualList.addAll(newListUiModel)
        } else {
            actualList.addAll(newListUiModel)
        }

        return actualList
    }

    private fun doOnLastItemScrolled() {
        getUsersListByPage(viewState.value.lastUserId)
    }

    private fun onOpenAlertDialog(errorMessage: String) {
        setState {
            copy(
                errorAlertDialogUiModel = this.errorAlertDialogUiModel.copy(
                    isShown = true,
                    message = errorMessage
                )
            )
        }
    }

    private fun onDialogConfirm() {
        setState {
            copy(
                errorAlertDialogUiModel = this.errorAlertDialogUiModel.copy(
                    isShown = false
                )
            )
        }
    }

    private fun onDialogDismiss() {
        setState {
            copy(
                errorAlertDialogUiModel = this.errorAlertDialogUiModel.copy(
                    isShown = false
                )
            )
        }
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