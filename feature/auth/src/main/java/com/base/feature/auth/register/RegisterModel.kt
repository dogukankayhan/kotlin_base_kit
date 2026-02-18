package com.base.feature.auth.register

import com.base.ui.base.UiEffect
import com.base.ui.base.UiEvent
import com.base.ui.base.UiState

sealed class RegisterEvent : UiEvent {
    data class OnEmailChanged(val email: String) : RegisterEvent()
    data class OnPasswordChanged(val password: String) : RegisterEvent()
    data class OnNameChanged(val name: String) : RegisterEvent()
    object OnRegisterClicked : RegisterEvent()
    object OnLoginClicked : RegisterEvent()
}

data class RegisterState(
    val email: String = "",
    val password: String = "",
    val name: String = "",
    val isLoading: Boolean = false
) : UiState

sealed class RegisterEffect : UiEffect {
    data class ShowError(val message: String) : RegisterEffect()
}
