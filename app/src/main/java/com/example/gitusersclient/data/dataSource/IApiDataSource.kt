package com.example.gitusersclient.data.dataSource

import com.example.core.common.DataResult
import com.example.data_network.model.responce.UserRemoteModel

interface IApiDataSource {
    suspend fun fetchUsersList(pageNumber: Int): DataResult<List<UserRemoteModel>>
}