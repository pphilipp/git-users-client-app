package com.example.gitusersclient.data.dataSource

import com.example.core.common.DataResult
import com.example.core.common.GeneralException
import com.example.data_network.model.responce.UserDetailsRM
import com.example.data_network.model.responce.UserRM
import com.example.data_network.service.ApiService

class ApiDataSourceImpl(
    private val apiService: ApiService
) : IApiDataSource {

    override suspend fun fetchUsersList(
        pageNumber: Int
    ): DataResult<List<UserRM>> = try {
        val response = apiService.fetchUsersList()

        if (response.isSuccessful) {
            response.body()?.let {
                DataResult.Success(it)
            } ?: DataResult.Error(GeneralException(response.code().toString(), response.message()))
        } else {
            DataResult.Error(GeneralException())
        }
    } catch (e: Exception) {
        DataResult.Error(GeneralException(code = e.cause.toString(), message = e.message))
    }

    override suspend fun fetchUserDetails(
        userLogin: String
    ): DataResult<UserDetailsRM> = try {
        val response = apiService.fetchUserDetails(userLogin)

        if (response.isSuccessful) {
            response.body()?.let {
                DataResult.Success(it)
            } ?: DataResult.Error(GeneralException(response.code().toString(), response.message()))
        } else {
            DataResult.Error(GeneralException())
        }
    } catch (e: Exception) {
        DataResult.Error(GeneralException(code = e.cause.toString(), message = e.message))
    }
}