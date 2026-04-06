package com.base.feature.movies.dashboard

import com.base.model.Movie
import com.base.ui.base.UiEffect
import com.base.ui.base.UiEvent
import com.base.ui.base.UiState

data class DashboardState(
    val isLoading: Boolean = false,
    val popularMovies: List<Movie> = emptyList(),
    val trendingMovies: List<Movie> = emptyList(),
    val topRatedMovies: List<Movie> = emptyList(),
    val errorMessage: String? = null
) : UiState

sealed interface DashboardEvent : UiEvent {
    data object LoadData : DashboardEvent
    data class OnMovieClicked(val movieId: Int) : DashboardEvent
}

sealed interface DashboardEffect : UiEffect {
    data class NavigateToDetail(val movieId: Int) : DashboardEffect
}
