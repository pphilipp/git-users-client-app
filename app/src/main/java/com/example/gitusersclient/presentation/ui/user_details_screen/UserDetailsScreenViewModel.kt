package com.example.gitusersclient.presentation.ui.user_details_screen

import androidx.lifecycle.viewModelScope
import com.example.core.abstraction.data.IRepository
import com.example.core.abstraction.domain.IBusinessModel
import com.example.core.abstraction.presentation.base.BaseViewModel
import com.example.core.common.DataResult
import com.example.gitusersclient.domain.model.UserDetailsBM
import kotlinx.coroutines.launch

class UserDetailsScreenViewModel(
    private val repository: IRepository
) : BaseViewModel<UserDetailsScreenEvent, UserDetailsScreenViewState>() {

    override fun handleEvent(event: UserDetailsScreenEvent) {
        when (event) {
            is UserDetailsScreenEvent.InitializeEvent -> getUserDetailsByLogin(event.userLogin)
        }
    }

    private fun getUserDetailsByLogin(userLogin: String) {
        viewModelScope.launch {
            showProgress()

            when (val userDetails = repository.getUserDetails(userLogin)) {
                is DataResult.Success -> {
                    hideProgress()
                    doOnSuccessGetUserDetails(userDetails)
                }

                is DataResult.Error -> {
                    // show error state
                    hideProgress()
                }
            }
        }
    }

    private fun doOnSuccessGetUserDetails(
        userDetails: DataResult.Success<IBusinessModel>
    ) {
        (userDetails.data as UserDetailsBM).let { model ->
            setState {
                copy(
                    userDetailsUiModel = UserDetailsUiModel(
                        id = model.id,
                        login = model.login,
                        name = model.name,
                        avatarUrl = model.avatarUrl,
                        company = model.company,
                        location = model.location,
                        bio = model.bio
                    )
                )
            }
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

    override fun getInitialViewState(): UserDetailsScreenViewState = UserDetailsScreenViewState()
}