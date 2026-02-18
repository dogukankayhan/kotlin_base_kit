package com.base.ui.base


// If using Coroutines, viewModelScope is enough.

abstract class LifecycleViewModel<Event : UiEvent, State : UiState, Effect : UiEffect> : 
    BaseViewModel<Event, State, Effect>() {

    open fun onCreate() {}
    open fun onStart() {}
    open fun onResume() {}
    open fun onPause() {}
    open fun onStop() {}
    open fun onDestroy() {}
}
