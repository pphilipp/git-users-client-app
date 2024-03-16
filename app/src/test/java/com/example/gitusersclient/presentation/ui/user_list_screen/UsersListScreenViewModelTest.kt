package com.example.gitusersclient.presentation.ui.user_list_screen

import com.example.core.abstraction.data.IRepository
import com.example.core.abstraction.domain.IBusinessModel
import com.example.core.common.DataResult
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@OptIn(DelicateCoroutinesApi::class)
class UsersListScreenViewModelTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")
    private lateinit var viewModel: UsersListScreenViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
        viewModel = UsersListScreenViewModel(FakeRepositoryImpl())
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }
    @Test
    fun initializeEventTest() = runTest {
        viewModel.handleEvent(UsersListScreenEvent.InitializeEvent)

        viewModel.viewState

        assert(viewModel.viewState.value.usersList?.size == 4)
    }
}

class FakeRepositoryImpl : IRepository {
    override suspend fun getUsersList(
        pageNumber: Int
    ): DataResult<List<IBusinessModel>> = DataResult.Success(
        listOf(mockUser, mockUser, mockUser, mockUser)
    )

    override suspend fun getUserDetails(
        userLogin: String
    ): DataResult<IBusinessModel> = DataResult.Success(object : IBusinessModel {})
}