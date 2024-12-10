package com.example.ptiapplicationv2.presentation.caclucatedate

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ptiapplicationv2.domain.PtiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CalculateDateViewModel @Inject constructor(
    private val ptiRepository: PtiRepository
) : ViewModel() {

    fun init() {
        viewModelScope.launch {
            val result = ptiRepository.calculateDeadLine(carNumber = "uu657vv")
            result.fold(onSuccess = { result ->
                Log.d("vaimee", result.toString())
            }, onFailure = { error ->
                Log.d("vaimee", error.toString())
            })
        }
    }
}