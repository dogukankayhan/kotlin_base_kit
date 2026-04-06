package com.base.feature.movies.detail

import com.base.model.MovieDetail
import com.base.ui.base.UiEffect
import com.base.ui.base.UiEvent
import com.base.ui.base.UiState

data class MovieDetailState(
    val isLoading: Boolean = false,
    val movieDetail: MovieDetail? = null,
    val isFavorite: Boolean = false,
    val errorMessage: String? = null
) : UiState

sealed interface MovieDetailEvent : UiEvent {
    data class LoadMovie(val movieId: Int) : MovieDetailEvent
    data object OnFavoriteClick : MovieDetailEvent
    data object OnBackClicked : MovieDetailEvent
}

sealed interface MovieDetailEffect : UiEffect {
    data object NavigateBack : MovieDetailEffect
    data class ShowToast(val message: String) : MovieDetailEffect
}
