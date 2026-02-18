package com.example.kotlinbasekit.ui

import com.base.ui.base.BaseViewModel
import com.base.ui.base.UiEffect
import com.base.ui.base.UiEvent
import com.base.ui.base.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : BaseViewModel<MainEvent, MainState, MainEffect>() {

    override fun createInitialState(): MainState = MainState()

    override fun handleEvent(event: MainEvent) {
        when (event) {
            is MainEvent.LoadDat -> {
                setState { copy(isLoading = true) }
                // Simulate data loading
                setState { copy(isLoading = false, data = "Loaded Data") }
                setEffect(MainEffect.ShowToast("Data Loaded"))
            }
        }
    }
}

sealed class MainEvent : UiEvent {
    object LoadDat : MainEvent()
}

data class MainState(
    val isLoading: Boolean = false,
    val data: String? = null
) : UiState

sealed class MainEffect : UiEffect {
    data class ShowToast(val message: String) : MainEffect()
}
