package com.example.ptiapplicationv2.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
sealed class SagencyResultApiModel {
    @JsonClass(generateAdapter = true)
    data class ErrorModelApi(val value: String) : SagencyResultApiModel()

    @JsonClass(generateAdapter = true)
    data class SagencyResultApi(
        @Json(name = "SeatsCount")
        val seatsCount: Int,
        val message: String,
        val ptiEndDate: String,
        val ptiStartDate: String,
        val ptiStatus: Int,
        val requestDate: String,
        val techStatus: Int
    ) : SagencyResultApiModel()
}
