package com.example.gitusersclient.data.dataSource

import com.example.core.common.DataResult
import com.example.core.common.GeneralException
import com.example.data_network.model.responce.UserRemoteModel
import com.example.data_network.service.ApiService

class ApiDataSourceImpl(
    private val apiService: ApiService
) : IApiDataSource {

    override suspend fun fetchUsersList(
        pageNumber: Int
    ): DataResult<List<UserRemoteModel>> = try {
        val usersListResponse = apiService.fetchUsersList()

        if (usersListResponse.isSuccessful) {
            usersListResponse.body()?.let {
                DataResult.Success(it)
            } ?: DataResult.Error(
                GeneralException(
                    code = "${usersListResponse.code()}",
                    message = usersListResponse.message()
                )
            )
        } else {
            DataResult.Error(GeneralException())
        }
    } catch (e: Exception) {
        DataResult.Error(GeneralException(code = e.cause.toString(), message = e.message))
    }


}