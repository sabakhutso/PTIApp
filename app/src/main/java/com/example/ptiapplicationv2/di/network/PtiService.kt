package com.example.ptiapplicationv2.di.network

import com.example.ptiapplicationv2.data.model.CalculateDeadLineApiModel
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Path

interface PtiService {

    @POST("{code}")
    suspend fun calculateDeadLine(@Path("code") code: String): Response<CalculateDeadLineApiModel>
}