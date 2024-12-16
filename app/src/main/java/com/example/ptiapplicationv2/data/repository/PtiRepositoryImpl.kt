package com.example.ptiapplicationv2.data.repository

import com.example.ptiapplicationv2.data.model.CalculateDeadLineApiModel
import com.example.ptiapplicationv2.data.model.SagencyResultApiModel
import com.example.ptiapplicationv2.di.DispatcherProvider
import com.example.ptiapplicationv2.di.network.PtiService
import com.example.ptiapplicationv2.domain.PtiRepository
import com.example.ptiapplicationv2.domain.model.CalculateDeadLineResult
import com.example.ptiapplicationv2.domain.model.CalculateDeadLineResult.SagencyResult
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PtiRepositoryImpl @Inject constructor(
    private val ptiService: PtiService,
    private val dispatcherProvider: DispatcherProvider
) : PtiRepository {

    override suspend fun calculateDeadLine(
        carNumber: String
    ): Result<CalculateDeadLineResult> = withContext(context = dispatcherProvider.io()) {
        try {
            val calculateDeadLine = ptiService.calculateDeadLine(carNumber)
            val responseBody = calculateDeadLine.body()!!
            if (calculateDeadLine.isSuccessful) Result.success(responseBody.toDomain())
            else Result.failure(IllegalStateException("Response wasn't successful"))
        } catch (e: Exception) {
            Result.failure(e)
        } catch (e: CancellationException) {
            throw e
        }
    }
}

fun CalculateDeadLineApiModel.toDomain(): CalculateDeadLineResult {
    return CalculateDeadLineResult(
        govNum = govNum,
        sagencyResult = sagencyResult.toDomain(),
        status = status,
        vehicleId = vehicleId
    )
}

fun SagencyResultApiModel.toDomain(): SagencyResult {
    return SagencyResult(
        seatsCount = seatsCount,
        message = message,
        ptiEndDate = ptiEndDate,
        ptiStartDate = ptiStartDate,
        ptiStatus = ptiStatus,
        requestDate = requestDate,
        techStatus = techStatus
    )
}
