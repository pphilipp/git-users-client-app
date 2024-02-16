package com.example.core.common

import com.example.core.abstraction.data.api.model.IRemoteModel
import com.example.core.abstraction.domain.IBusinessModel

sealed class DataResult<out T> {
    class Success<out T>(val data: T) : DataResult<T>()
    class Error(val errorBody: GeneralException) : DataResult<Nothing>()
}

fun <RM : IRemoteModel, BM : IBusinessModel> DataResult<RM>.toDomainResult(
    errorMapper: (GeneralException) -> GeneralException = { it },
    successMapper: (RM) -> BM
): DataResult<BM> {
    return when (this) {
        is DataResult.Success -> DataResult.Success(successMapper(this.data))
        is DataResult.Error -> DataResult.Error(errorMapper(this.errorBody))
    }
}