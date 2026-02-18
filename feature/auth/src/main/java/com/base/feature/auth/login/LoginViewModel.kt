package com.base.feature.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.base.auth.AuthManager
import com.base.ui.base.BaseViewModel
import com.base.ui.base.UiEffect
import com.base.ui.base.UiEvent
import com.base.ui.base.UiState
import com.base.feature.auth.coordinator.AuthCoordinator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authManager: AuthManager,
    private val coordinator: com.base.feature.auth.coordinator.AuthCoordinator
) : BaseViewModel<LoginEvent, LoginState, LoginEffect>() {

    override fun createInitialState(): LoginState = LoginState()

    override fun handleEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.OnEmailChanged -> setState { copy(email = event.email) }
            is LoginEvent.OnPasswordChanged -> setState { copy(password = event.password) }
            is LoginEvent.OnLoginClicked -> login()
            is LoginEvent.OnRegisterClicked -> navigateToRegister()
        }
    }

    private fun navigateToRegister() {
        viewModelScope.launch {
            coordinator.navigateToRegister()
        }
    }

    private fun login() {
        val email = uiState.value.email
        val password = uiState.value.password

        if (email.isBlank() || password.isBlank()) {
            setEffect(LoginEffect.ShowError("Please fill all fields"))
            return
        }

        viewModelScope.launch {
            setState { copy(isLoading = true) }
            val result = authManager.login(email, password)
            setState { copy(isLoading = false) }

            result.onSuccess {
                coordinator.navigateToHome()
            }.onFailure { exception ->
                val errorMessage = when (exception) {
                    is com.base.network.model.ApiException.NetworkError -> 
                        "Network connection error. Please check your internet."
                    is com.base.network.model.ApiException.TimeoutError -> 
                        "Request timed out. Please try again."
                    is com.base.network.model.ApiException.HttpError -> 
                        exception.message ?: "Server error occurred."
                    else -> exception.message ?: "An unexpected error occurred"
                }
                setEffect(LoginEffect.ShowError(errorMessage))
            }
        }
    }
}
