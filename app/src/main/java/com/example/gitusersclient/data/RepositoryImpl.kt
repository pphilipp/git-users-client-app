package com.example.gitusersclient.data

import com.example.core.abstraction.data.IRepository
import com.example.core.abstraction.domain.IRemoteToBusinessMapper
import com.example.core.common.DataResult
import com.example.core.common.toDomainResult
import com.example.data_network.model.responce.UserDetailsRM
import com.example.data_network.model.responce.UserRM
import com.example.gitusersclient.data.dataSource.ApiDataSourceImpl
import com.example.gitusersclient.domain.model.UserBM
import com.example.gitusersclient.domain.model.UserDetailsBM

class RepositoryImpl(
    private val apiDataSource: ApiDataSourceImpl,
    private val userItemMapper: IRemoteToBusinessMapper<UserRM, UserBM>,
    private val userDetailsMapper: IRemoteToBusinessMapper<UserDetailsRM, UserDetailsBM>,
) : IRepository {

    override suspend fun getUsersList(pageNumber: Int): DataResult<List<UserBM>> =
        when (val result = apiDataSource.fetchUsersList(pageNumber)) {
            is DataResult.Success -> DataResult.Success(
                result.data.map {
                    userItemMapper.remoteToBusiness(it)
                }
            )

            is DataResult.Error -> DataResult.Error(result.errorBody)
        }

    override suspend fun getUserDetails(
        userLogin: String
    ): DataResult<UserDetailsBM> {

        return apiDataSource.fetchUserDetails(userLogin).toDomainResult(
            errorMapper = {
                userDetailsMapper.mapToException(it)
            },
            successMapper = {
                userDetailsMapper.remoteToBusiness(it)
            }
        )
    }
}
