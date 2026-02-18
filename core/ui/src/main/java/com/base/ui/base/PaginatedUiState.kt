package com.base.ui.base

interface PaginatedUiState<T> : UiState {
    val items: List<T>
    val isLoading: Boolean
    val error: String?
    val page: Int
    val isLastPage: Boolean
}
