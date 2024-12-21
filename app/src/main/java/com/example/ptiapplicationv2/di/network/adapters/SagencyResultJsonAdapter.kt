package com.example.ptiapplicationv2.di.network.adapters

import com.example.ptiapplicationv2.data.model.SagencyResultApiModel
import com.example.ptiapplicationv2.data.model.SagencyResultApiModel.SagencyResultApi
import com.example.ptiapplicationv2.data.model.SagencyResultApiModel.ErrorModelApi
import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.ToJson

class SagencyResultJsonAdapter {

    @FromJson
    fun fromJson(
        jsonReader: JsonReader,
        delegate: JsonAdapter<SagencyResultApi>
    ): SagencyResultApiModel? = if (jsonReader.peek() == JsonReader.Token.BEGIN_OBJECT) {
        delegate.fromJson(jsonReader)
    } else {
        ErrorModelApi(jsonReader.nextString())
    }

    @ToJson
    fun toJson(
        jsonWriter: JsonWriter,
        data: SagencyResultApiModel,
        delegate: JsonAdapter<SagencyResultApi>
    ) {
        when (data) {
            is SagencyResultApi -> delegate.toJson(jsonWriter, data)
            is ErrorModelApi -> jsonWriter.value(data.value)
        }
    }
}