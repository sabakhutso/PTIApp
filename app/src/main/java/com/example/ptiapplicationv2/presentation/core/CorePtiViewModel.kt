package com.example.ptiapplicationv2.presentation.core

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.properties.Delegates

// TODO testing file for core pti viewModel testing purposes ( might be removed in future )
interface CoreViewModelState

interface UiState

interface UiAction

interface ContentMapper<UiState> {
    fun buildContent(state: CoreViewModelState): List<UiState>
}


class CorePtiViewModel<STATE : CoreViewModelState, CONTENT : UiState, ACTION : UiAction>(
    initialState: STATE,
    uiMapper: ContentMapper<CONTENT>
) : ViewModel() {

    // TODO make it protected for vm usages
    private val uiStateObserver = MutableLiveData<List<UiState>>()
    private val uiActionObserver = MutableLiveData<ACTION>()

    private var state: CoreViewModelState by Delegates.observable(initialState) { _, oldState, newState ->
        if (oldState != newState) {
            val contentList = uiMapper.buildContent(newState)
            uiStateObserver.value = contentList
        }
    }

    // TODO make it protected for vm usages
    private fun onEvent(event: ACTION) {
        uiActionObserver.value = event
    }
}