package com.base.feature.auth.register

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
class RegisterViewModel @Inject constructor(
    private val authManager: AuthManager,
    private val coordinator: com.base.feature.auth.coordinator.AuthCoordinator
) : BaseViewModel<RegisterEvent, RegisterState, RegisterEffect>() {

    override fun createInitialState(): RegisterState = RegisterState()

    override fun handleEvent(event: RegisterEvent) {
        when (event) {
            is RegisterEvent.OnEmailChanged -> setState { copy(email = event.email) }
            is RegisterEvent.OnPasswordChanged -> setState { copy(password = event.password) }
            is RegisterEvent.OnNameChanged -> setState { copy(name = event.name) }
            is RegisterEvent.OnRegisterClicked -> register()
            is RegisterEvent.OnLoginClicked -> navigateToLogin()
        }
    }

    private fun navigateToLogin() {
        viewModelScope.launch {
            coordinator.navigateToLogin()
        }
    }

    private fun register() {
        val email = uiState.value.email
        val password = uiState.value.password
        val name = uiState.value.name

        if (email.isBlank() || password.isBlank() || name.isBlank()) {
            setEffect(RegisterEffect.ShowError("Please fill all fields"))
            return
        }

        viewModelScope.launch {
            setState { copy(isLoading = true) }
            val result = authManager.register(email, password, name)
            setState { copy(isLoading = false) }

            result.onSuccess {
                coordinator.navigateToDashboard()
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
                setEffect(RegisterEffect.ShowError(errorMessage))
            }
        }
    }
}
