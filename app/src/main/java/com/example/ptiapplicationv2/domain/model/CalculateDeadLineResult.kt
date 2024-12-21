package com.example.ptiapplicationv2.domain.model

data class CalculateDeadLineResult(
    val govNum: String,
    val sagencyResult: SagencyResultDomain,
    val status: Int,
    val vehicleId: String
) {
    sealed class SagencyResultDomain {
        data class SagencyResult(
            val seatsCount: Int,
            val message: String,
            val ptiEndDate: String,
            val ptiStartDate: String,
            val ptiStatus: Int,
            val requestDate: String,
            val techStatus: Int
        ) : SagencyResultDomain()

        data class ErrorDomain(
            val value: String
        ) : SagencyResultDomain()
    }
}