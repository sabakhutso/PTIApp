package com.example.ptiapplicationv2.presentation.intro

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow

class IntroViewModel : ViewModel() {

    private val mutableActionObserver = Channel<IntroUiAction>()
    val actionObserver: Flow<IntroUiAction> = mutableActionObserver.receiveAsFlow()

    fun openCalculateDatePage() = mutableActionObserver.trySend(IntroUiAction.CalculateDatePage)
    fun openAuthorizationPage() = mutableActionObserver.trySend(IntroUiAction.AuthorizationPage)
}
