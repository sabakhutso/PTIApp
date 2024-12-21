package com.example.ptiapplicationv2.presentation.caclucatedate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ptiapplicationv2.domain.PtiRepository
import com.example.ptiapplicationv2.domain.model.CalculateDeadLineResult.SagencyResultDomain.SagencyResult
import com.example.ptiapplicationv2.domain.model.CalculateDeadLineResult.SagencyResultDomain.ErrorDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.ptiapplicationv2.presentation.caclucatedate.CalculateDateEvent.ShowErrorToast
import com.example.ptiapplicationv2.presentation.caclucatedate.CalculateDateEvent.NavigateToErrorPage
import com.example.ptiapplicationv2.presentation.caclucatedate.CalculateDateEvent.NavigateToDetails

@HiltViewModel
class CalculateDateViewModel @Inject constructor(
    private val ptiRepository: PtiRepository
) : ViewModel() {

    private val _events = Channel<CalculateDateEvent>()
    val events: Flow<CalculateDateEvent> = _events.receiveAsFlow()

    fun calculateDeadLine(cardNumber: String) = viewModelScope.launch {
        val result = ptiRepository.calculateDeadLine(carNumber = cardNumber)
        result.fold(onSuccess = { result ->
            when (result.sagencyResult) {
                is ErrorDomain -> _events.trySend(NavigateToErrorPage(errorDomain = result.sagencyResult))
                is SagencyResult -> _events.trySend(NavigateToDetails(successData = result.sagencyResult))
            }
        }, onFailure = { error -> _events.trySend(element = ShowErrorToast) })
    }
}