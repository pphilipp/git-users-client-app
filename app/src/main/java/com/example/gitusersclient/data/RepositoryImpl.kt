package com.example.gitusersclient.data

import com.example.core.abstraction.data.IRepository
import com.example.core.abstraction.domain.IRemoteToBusinessMapper
import com.example.core.common.DataResult
import com.example.data_network.model.responce.UserRemoteModel
import com.example.gitusersclient.data.dataSource.ApiDataSourceImpl
import com.example.gitusersclient.domain.model.UserBusinessModel

class RepositoryImpl(
    private val apiDataSource: ApiDataSourceImpl,
    private val userItemMapper: IRemoteToBusinessMapper<UserRemoteModel, UserBusinessModel>,
) : IRepository {

    override suspend fun getUsersList(pageNumber: Int): DataResult<List<UserBusinessModel>> =
        when (val result = apiDataSource.fetchUsersList(pageNumber)) {
            is DataResult.Success -> DataResult.Success(
                result.data.map {
                    userItemMapper.remoteToBusiness(it)
                }
            )

            is DataResult.Error -> DataResult.Error(result.errorBody)
        }
}
