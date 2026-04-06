package com.base.feature.movies.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.base.domain.usecase.GetMovieDetailsUseCase
import com.base.domain.usecase.IsFavoriteUseCase
import com.base.domain.usecase.AddFavoriteUseCase
import com.base.domain.usecase.RemoveFavoriteUseCase
import com.base.domain.result.Result
import com.base.model.Movie
import com.base.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val isFavoriteUseCase: IsFavoriteUseCase,
    private val addFavoriteUseCase: AddFavoriteUseCase,
    private val removeFavoriteUseCase: RemoveFavoriteUseCase,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<MovieDetailEvent, MovieDetailState, MovieDetailEffect>() {

    override fun createInitialState(): MovieDetailState = MovieDetailState()

    init {
        savedStateHandle.get<String>("movieId")?.toIntOrNull()?.let { movieId ->
            handleEvent(MovieDetailEvent.LoadMovie(movieId))
        }
    }

    override fun handleEvent(event: MovieDetailEvent) {
        when (event) {
            is MovieDetailEvent.LoadMovie -> loadMovieDetails(event.movieId)
            is MovieDetailEvent.OnFavoriteClick -> toggleFavorite()
            is MovieDetailEvent.OnBackClicked -> setEffect(MovieDetailEffect.NavigateBack)
        }
    }

    private fun loadMovieDetails(movieId: Int) {
        viewModelScope.launch {
            setState { copy(isLoading = true, errorMessage = null) }

            // Check favorite status parallel to fetching details
            launch {
                val isFavResult = isFavoriteUseCase(movieId)
                if (isFavResult is Result.Success) {
                    setState { copy(isFavorite = isFavResult.data) }
                }
            }

            val detailResult = getMovieDetailsUseCase(movieId)
            if (detailResult is Result.Success) {
                setState { 
                    copy(
                        isLoading = false,
                        movieDetail = detailResult.data
                    )
                }
            } else {
                setState { 
                    copy(
                        isLoading = false,
                        errorMessage = "Failed to load movie details."
                    )
                }
            }
        }
    }

    private fun toggleFavorite() {
        val detail = uiState.value.movieDetail ?: return
        val currentlyFavorite = uiState.value.isFavorite
        
        viewModelScope.launch {
            if (currentlyFavorite) {
                removeFavoriteUseCase(detail.id)
                setState { copy(isFavorite = false) }
                setEffect(MovieDetailEffect.ShowToast("Removed from favorites"))
            } else {
                val simpleMovie = Movie(
                    id = detail.id,
                    title = detail.title,
                    overview = detail.overview,
                    posterPath = detail.posterPath,
                    backdropPath = detail.backdropPath,
                    releaseDate = detail.releaseDate,
                    voteAverage = detail.voteAverage,
                    voteCount = 0,
                    genreIds = detail.genres.map { it.id }
                )
                addFavoriteUseCase(simpleMovie)
                setState { copy(isFavorite = true) }
                setEffect(MovieDetailEffect.ShowToast("Added to favorites"))
            }
        }
    }
}
