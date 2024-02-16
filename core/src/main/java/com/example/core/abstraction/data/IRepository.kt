package com.example.core.abstraction.data

import com.example.core.abstraction.domain.IBusinessModel
import com.example.core.common.DataResult

interface IRepository {
    suspend fun getUsersList(pageNumber: Int): DataResult<List<IBusinessModel>>
    suspend fun getUserDetails(userLogin: String): DataResult<IBusinessModel>
}