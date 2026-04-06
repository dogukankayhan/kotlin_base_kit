package com.base.feature.movies.dashboard

import com.base.model.Movie
import com.base.ui.base.UiEffect
import com.base.ui.base.UiEvent
import com.base.ui.base.UiState

import com.base.ui.base.PaginatedUiState

data class DashboardState(
    override val isLoading: Boolean = false,
    override val items: List<Movie> = emptyList(),
    override val error: String? = null,
    override val page: Int = 1,
    override val isLastPage: Boolean = false
) : PaginatedUiState<Movie>

sealed interface DashboardEvent : UiEvent {
    data object LoadData : DashboardEvent
    data object LoadNextPage : DashboardEvent
    data class OnMovieClicked(val movieId: Int) : DashboardEvent
}

sealed interface DashboardEffect : UiEffect {
    data class NavigateToDetail(val movieId: Int) : DashboardEffect
}
