package com.base.feature.auth.login

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.hilt.navigation.compose.hiltViewModel
import com.base.ui.components.BaseTextField
import com.base.ui.components.BaseButton
import com.base.common.validator.Validator
import com.base.common.validator.ValidationResult
import com.base.common.validator.rules.RequiredRule
import com.base.common.validator.rules.EmailRule
import com.base.common.validator.rules.MinLengthRule

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.uiEffect.collect { effect ->
            when (effect) {
                is LoginEffect.ShowError -> Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Login", style = MaterialTheme.typography.headlineMedium)
        
        Spacer(modifier = Modifier.height(32.dp))

        BaseTextField(
            value = state.email,
            onValueChange = { viewModel.setEvent(LoginEvent.OnEmailChanged(it)) },
            label = "Email",
            modifier = Modifier.fillMaxWidth(),
            validator = {
                val result = Validator.validate(it, listOf(RequiredRule(), EmailRule()))
                if (result is ValidationResult.Invalid) result.errorMessage else null
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        BaseTextField(
            value = state.password,
            onValueChange = { viewModel.setEvent(LoginEvent.OnPasswordChanged(it)) },
            label = "Password",
            modifier = Modifier.fillMaxWidth(),
            validator = {
                val result = Validator.validate(it, listOf(RequiredRule(), MinLengthRule(6)))
                if (result is ValidationResult.Invalid) result.errorMessage else null
            },
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(32.dp))

        val isEmailValid = Validator.validate(state.email, listOf(RequiredRule(), EmailRule())) is ValidationResult.Valid
        val isPasswordValid = Validator.validate(state.password, listOf(RequiredRule(), MinLengthRule(6))) is ValidationResult.Valid

        BaseButton(
            text = "Login",
            onClick = { viewModel.setEvent(LoginEvent.OnLoginClicked) },
            modifier = Modifier.fillMaxWidth(),
            isLoading = state.isLoading,
            enabled = isEmailValid && isPasswordValid
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        TextButton(onClick = { viewModel.setEvent(LoginEvent.OnRegisterClicked) }) {
            Text("Don't have an account? Register")
        }

    }
}
