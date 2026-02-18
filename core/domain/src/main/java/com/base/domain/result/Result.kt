// Ideally we should have a domain specific Result, but for now we can rely on standard 
// kotlin.Result or create a simple one.
// Let's create a domain specific Resource/Result class to avoid leaking network details 
// (like HTTP codes) to UI.

package com.base.domain.result

sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Throwable) : Result<Nothing>()
    object Loading : Result<Nothing>()
}
