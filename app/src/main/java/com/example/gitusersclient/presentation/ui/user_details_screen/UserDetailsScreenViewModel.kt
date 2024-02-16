package com.example.gitusersclient.presentation.ui.user_details_screen

import androidx.lifecycle.viewModelScope
import com.example.core.abstraction.data.IRepository
import com.example.core.abstraction.presentation.base.BaseViewModel
import com.example.core.common.DataResult
import com.example.gitusersclient.domain.model.UserDetailsBM
import kotlinx.coroutines.launch

class UserDetailsScreenViewModel(
    private val repository: IRepository
) : BaseViewModel<UserDetailsScreenEvent, UserDetailsScreenViewState>() {

    override fun handleEvent(event: UserDetailsScreenEvent) {
        when (event) {
            is UserDetailsScreenEvent.InitializeEvent -> {
                getUserDetailsByLogin(event.userLogin)
            }
        }
    }

    private fun getUserDetailsByLogin(userLogin: String) {
        viewModelScope.launch {
            when (val userDetails = repository.getUserDetails(userLogin)) {
                is DataResult.Success -> {
                    setState {
                        copy(
                            userName = (userDetails.data as UserDetailsBM).name
                        )
                    }
                }

                is DataResult.Error -> {
                    // show error state
                }
            }
        }
    }

    override fun getInitialViewState(): UserDetailsScreenViewState = UserDetailsScreenViewState()
}