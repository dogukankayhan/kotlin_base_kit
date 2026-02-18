package com.base.feature.auth.login

import com.base.ui.base.UiEffect
import com.base.ui.base.UiEvent
import com.base.ui.base.UiState

sealed class LoginEvent : UiEvent {
    data class OnEmailChanged(val email: String) : LoginEvent()
    data class OnPasswordChanged(val password: String) : LoginEvent()
    object OnLoginClicked : LoginEvent()
    object OnRegisterClicked : LoginEvent()
}

data class LoginState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false
) : UiState

sealed class LoginEffect : UiEffect {
    data class ShowError(val message: String) : LoginEffect()
}
