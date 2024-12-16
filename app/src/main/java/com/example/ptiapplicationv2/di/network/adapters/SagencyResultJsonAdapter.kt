package com.example.ptiapplicationv2.di.network.adapters

import com.example.ptiapplicationv2.data.model.SagencyResultApiModel
import com.example.ptiapplicationv2.data.model.SagencyResultApiModel.SagencyResult
import com.example.ptiapplicationv2.data.model.SagencyResultApiModel.StringData
import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.ToJson

class SagencyResultJsonAdapter {

    @FromJson
    fun fromJson(
        jsonReader: JsonReader,
        delegate: JsonAdapter<SagencyResult>
    ): SagencyResultApiModel? = if (jsonReader.peek() == JsonReader.Token.BEGIN_OBJECT) {
        delegate.fromJson(jsonReader)
    } else {
        StringData(jsonReader.nextString())
    }

    @ToJson
    fun toJson(
        jsonWriter: JsonWriter,
        data: SagencyResultApiModel,
        delegate: JsonAdapter<SagencyResult>
    ) {
        when (data) {
            is SagencyResult -> delegate.toJson(jsonWriter, data)
            is StringData -> jsonWriter.value(data.value)
        }
    }
}