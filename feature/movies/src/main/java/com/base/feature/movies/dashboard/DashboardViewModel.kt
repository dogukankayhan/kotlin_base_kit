package com.base.feature.movies.dashboard

import androidx.lifecycle.viewModelScope
import com.base.domain.usecase.GetPopularMoviesUseCase
import com.base.domain.result.Result
import com.base.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase
) : BaseViewModel<DashboardEvent, DashboardState, DashboardEffect>() {

    override fun createInitialState(): DashboardState = DashboardState()

    init {
        handleEvent(DashboardEvent.LoadData)
    }

    override fun handleEvent(event: DashboardEvent) {
        when (event) {
            is DashboardEvent.LoadData -> loadDashboardData()
            is DashboardEvent.OnMovieClicked -> setEffect(DashboardEffect.NavigateToDetail(event.movieId))
        }
    }

    private fun loadDashboardData() {
        if (uiState.value.popularMovies.isNotEmpty()) return

        viewModelScope.launch {
            setState { copy(isLoading = true, errorMessage = null) }
            
            val result = getPopularMoviesUseCase(1)
            if (result is Result.Success) {
                setState { 
                    copy(
                        isLoading = false, 
                        popularMovies = result.data.results
                    ) 
                }
            } else {
                setState { 
                    copy(
                        isLoading = false,
                        errorMessage = "Failed to load popular movies."
                    )
                }
            }
        }
    }
}
