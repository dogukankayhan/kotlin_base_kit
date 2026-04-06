package com.base.feature.movies.dashboard

import androidx.lifecycle.viewModelScope
import com.base.domain.usecase.GetPopularMoviesUseCase
import com.base.domain.result.Result
import com.base.model.Movie
import com.base.ui.base.PaginatedViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase
) : PaginatedViewModel<Movie, DashboardEvent, DashboardState, DashboardEffect>() {

    override fun createInitialState(): DashboardState = DashboardState()

    init {
        loadData(isRefresh = true)
    }

    override fun handleEvent(event: DashboardEvent) {
        when (event) {
            is DashboardEvent.LoadData -> loadData(isRefresh = true)
            is DashboardEvent.LoadNextPage -> loadData()
            is DashboardEvent.OnMovieClicked -> setEffect(DashboardEffect.NavigateToDetail(event.movieId))
        }
    }

    override fun updateState(
        items: List<Movie>,
        isLoading: Boolean,
        error: String?,
        page: Int,
        isLastPage: Boolean
    ) {
        setState {
            copy(
                items = items,
                isLoading = isLoading,
                error = error,
                page = page,
                isLastPage = isLastPage
            )
        }
    }

    override suspend fun fetchItems(page: Int): kotlin.Result<List<Movie>> {
        val result = getPopularMoviesUseCase(page)
        return if (result is com.base.domain.result.Result.Success) {
            kotlin.Result.success(result.data.results)
        } else {
            kotlin.Result.failure(Exception("Failed to load popular movies"))
        }
    }
}
