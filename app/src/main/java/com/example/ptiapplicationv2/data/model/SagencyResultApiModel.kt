package com.example.ptiapplicationv2.data.model

import androidx.annotation.Keep
import com.squareup.moshi.Json

@Keep
data class SagencyResultApiModel(
    @Json(name = "SeatsCount")
    val seatsCount: Int,
    val message: String,
    val ptiEndDate: String,
    val ptiStartDate: String,
    val ptiStatus: Int,
    val requestDate: String,
    val techStatus: Int
)