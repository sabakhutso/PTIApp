package com.example.ptiapplicationv2.domain

import com.example.ptiapplicationv2.domain.model.CalculateDeadLineResult

interface PtiRepository {

    suspend fun calculateDeadLine(carNumber: String): Result<CalculateDeadLineResult>
}
