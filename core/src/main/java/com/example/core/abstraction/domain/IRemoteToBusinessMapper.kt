package com.example.core.abstraction.domain

import com.example.core.abstraction.data.api.model.IRemoteModel
import com.example.core.common.GeneralException

interface IRemoteToBusinessMapper<R : IRemoteModel, BM : IBusinessModel> {

    fun remoteToBusiness(model: R): BM

    fun mapToException(exception: GeneralException): GeneralException = exception
}