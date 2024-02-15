package com.example.gitusersclient.di

import com.example.gitusersclient.presentation.ui.user_list_screen.UsersListScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {
    /**
     * Data layer dependency section { dataSource,  dataSourceToDataSource mappers }
     */


    /**
     * Domain layer dependency section { useCases, mappers }
     */


    /**
     * Presentation layer dependency section { ViewModels }
     */
    viewModel {
        UsersListScreenViewModel()
    }


}