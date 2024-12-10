package com.example.ptiapplicationv2.data.model

import androidx.annotation.Keep

@Keep
data class CalculateDeadLineApiModel(
    val govNum: String,
    val sagencyResult: SagencyResultApiModel,
    val status: Int,
    val vehicleId: String
)