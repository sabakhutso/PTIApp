package com.example.ptiapplicationv2.data.mapper

import com.example.ptiapplicationv2.data.model.CalculateDeadLineApiModel
import com.example.ptiapplicationv2.data.model.SagencyResultApiModel
import com.example.ptiapplicationv2.domain.model.CalculateDeadLineResult
import com.example.ptiapplicationv2.domain.model.CalculateDeadLineResult.SagencyResultDomain

fun CalculateDeadLineApiModel.toDomain(): CalculateDeadLineResult {
    return CalculateDeadLineResult(
        govNum = govNum,
        sagencyResult = sagencyResult.toDomain(),
        status = status,
        vehicleId = vehicleId
    )
}

fun SagencyResultApiModel.toDomain(): SagencyResultDomain {
    return when (this) {
        is SagencyResultApiModel.SagencyResultApi -> SagencyResultDomain.SagencyResult(
            seatsCount = seatsCount,
            message = message,
            ptiEndDate = ptiEndDate,
            ptiStartDate = ptiStartDate,
            ptiStatus = ptiStatus,
            requestDate = requestDate,
            techStatus = techStatus
        )

        is SagencyResultApiModel.ErrorModelApi -> SagencyResultDomain.ErrorDomain(value)
    }
}
