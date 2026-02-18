package com.base.ui.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.base.ui.base.LifecycleViewModel

@Composable
fun <Event : com.base.ui.base.UiEvent, State : com.base.ui.base.UiState, Effect : com.base.ui.base.UiEffect> 
ComposableLifecycle(
    viewModel: LifecycleViewModel<Event, State, Effect>,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
) {
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_CREATE -> viewModel.onCreate()
                Lifecycle.Event.ON_START -> viewModel.onStart()
                Lifecycle.Event.ON_RESUME -> viewModel.onResume()
                Lifecycle.Event.ON_PAUSE -> viewModel.onPause()
                Lifecycle.Event.ON_STOP -> viewModel.onStop()
                Lifecycle.Event.ON_DESTROY -> viewModel.onDestroy()
                else -> {}
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
}
