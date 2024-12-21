package com.example.ptiapplicationv2.presentation.caclucatedate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ptiapplicationv2.domain.PtiRepository
import com.example.ptiapplicationv2.domain.model.CalculateDeadLineResult.SagencyResultDomain.SagencyResult
import com.example.ptiapplicationv2.domain.model.CalculateDeadLineResult.SagencyResultDomain.ErrorDomain
import com.example.ptiapplicationv2.presentation.caclucatedate.CalculateDateEvent.CategoryInputError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.ptiapplicationv2.presentation.caclucatedate.CalculateDateEvent.ShowErrorToast
import com.example.ptiapplicationv2.presentation.caclucatedate.CalculateDateEvent.NavigateToErrorPage
import com.example.ptiapplicationv2.presentation.caclucatedate.CalculateDateEvent.NavigateToDetails
import com.example.ptiapplicationv2.presentation.caclucatedate.CalculateDateEvent.NumberInputError
import com.example.ptiapplicationv2.presentation.caclucatedate.CalculateDateEvent.ShowDualInputError


@HiltViewModel
class CalculateDateViewModel @Inject constructor(
    private val ptiRepository: PtiRepository
) : ViewModel() {

    private val _events = Channel<CalculateDateEvent>()
    val events: Flow<CalculateDateEvent> = _events.receiveAsFlow()

    fun calculateDeadLine(cardNumber: String, category: String) {
        when {
            cardNumber.isEmpty() && category.isEmpty() -> _events.trySend(ShowDualInputError)
            cardNumber.isEmpty() -> _events.trySend(NumberInputError)
            category.isEmpty() -> _events.trySend(CategoryInputError)
            else -> callService(cardNumber)
        }
    }

    private fun callService(cardNumber: String) = viewModelScope.launch {
        val result = ptiRepository.calculateDeadLine(carNumber = cardNumber)
        result.fold(onSuccess = { result ->
            _events.trySend(
                element = when (result.sagencyResult) {
                    is ErrorDomain -> NavigateToErrorPage(result.sagencyResult)
                    is SagencyResult -> NavigateToDetails(result.sagencyResult)
                }
            )
        }, onFailure = { error -> _events.trySend(element = ShowErrorToast) })
    }
}