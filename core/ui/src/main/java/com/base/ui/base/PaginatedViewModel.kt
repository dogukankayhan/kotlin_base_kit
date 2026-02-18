package com.base.ui.base

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

abstract class PaginatedViewModel<Item, Event : UiEvent, State : PaginatedUiState<Item>, Effect : UiEffect> : 
    LifecycleViewModel<Event, State, Effect>() {

    protected var currentPage = 1
    protected var isLastPage = false
    protected var isLoadingLocal = false

    abstract fun updateState(
        items: List<Item>,
        isLoading: Boolean,
        error: String?,
        page: Int,
        isLastPage: Boolean
    )

    abstract suspend fun fetchItems(page: Int): Result<List<Item>>

    fun loadData(isRefresh: Boolean = false) {
        if (isLoadingLocal) return
        if (isRefresh) {
            currentPage = 1
            isLastPage = false
        } else if (isLastPage) {
            return
        }

        isLoadingLocal = true
        // Optimistically update loading state
        updateState(
            items = if (isRefresh) emptyList() else uiState.value.items,
            isLoading = true,
            error = null,
            page = currentPage,
            isLastPage = isLastPage
        )

        viewModelScope.launch {
            val result = fetchItems(currentPage)
            isLoadingLocal = false

            result.onSuccess { newItems ->
                val currentItems = if (isRefresh) emptyList() else uiState.value.items
                val allItems = currentItems + newItems
                
                isLastPage = newItems.isEmpty() // Simple check, or check explicit page size
                if (!isLastPage) currentPage++

                updateState(
                    items = allItems,
                    isLoading = false,
                    error = null,
                    page = currentPage,
                    isLastPage = isLastPage
                )
            }.onFailure { error ->
                updateState(
                    items = uiState.value.items,
                    isLoading = false,
                    error = error.message,
                    page = currentPage,
                    isLastPage = isLastPage
                )
            }
        }
    }
}
