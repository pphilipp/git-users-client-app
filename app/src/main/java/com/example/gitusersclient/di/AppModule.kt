package com.example.gitusersclient.di

import com.example.core.abstraction.data.IRepository
import com.example.core.abstraction.domain.IRemoteToBusinessMapper
import com.example.data_network.model.responce.UserRemoteModel
import com.example.gitusersclient.data.dataSource.ApiDataSourceImpl
import com.example.gitusersclient.data.RepositoryImpl
import com.example.gitusersclient.domain.UserItemMapperImpl
import com.example.gitusersclient.domain.model.UserBusinessModel
import com.example.gitusersclient.presentation.ui.user_details_screen.UserDetailsScreenViewModel
import com.example.gitusersclient.presentation.ui.user_list_screen.UsersListScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import org.koin.dsl.module

val appModule = module {
    /**
     * Data layer dependency section { dataSource,  dataSourceToDataSource mappers }
     */

    single { ApiDataSourceImpl(get()) }
    single<IRepository> { provideRepository() }

    /**
     * Domain layer dependency section { useCases, mappers }
     */
    factory<IRemoteToBusinessMapper<UserRemoteModel, UserBusinessModel>>(
        named(UserItemMapperImpl::class.java.name)
    ) {
        UserItemMapperImpl()
    }

    /**
     * Presentation layer dependency section { ViewModels }
     */
    viewModel {
        UsersListScreenViewModel(
            repository = get()
        )
    }

    viewModel {
        UserDetailsScreenViewModel(
            repository = get()
        )
    }
}

private fun Scope.provideRepository() = RepositoryImpl(
    apiDataSource = get(),
    userItemMapper = get(named(UserItemMapperImpl::class.java.name)),
)