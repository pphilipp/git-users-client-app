package com.example.gitusersclient.data.dataSource

import com.example.core.common.DataResult
import com.example.data_network.model.responce.UserDetailsRM
import com.example.data_network.model.responce.UserRM

interface IApiDataSource {
    suspend fun fetchUsersList(pageNumber: Int): DataResult<List<UserRM>>
    suspend fun fetchUserDetails(userLogin: String): DataResult<UserDetailsRM>
}