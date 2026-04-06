package com.base.domain.repository

import com.base.model.Movie
import com.base.model.MovieCreditResponse
import com.base.model.MovieDetail
import com.base.model.MovieResponse
import com.base.model.MovieReview
import com.base.model.MovieVideoResponse

interface MovieRepository {
    suspend fun getPopularMovies(page: Int): MovieResponse<Movie>
    suspend fun getNowPlayingMovies(page: Int): MovieResponse<Movie>
    suspend fun getTopRatedMovies(page: Int): MovieResponse<Movie>
    suspend fun getUpcomingMovies(page: Int): MovieResponse<Movie>
    suspend fun getTrendingMovies(): MovieResponse<Movie>
    suspend fun searchMovies(query: String, page: Int): MovieResponse<Movie>
    suspend fun getMovieDetails(movieId: Int): MovieDetail
    suspend fun getMovieCredits(movieId: Int): MovieCreditResponse
    suspend fun getMovieVideos(movieId: Int): MovieVideoResponse
    suspend fun getMovieReviews(movieId: Int, page: Int): MovieResponse<MovieReview>
    suspend fun getSimilarMovies(movieId: Int, page: Int): MovieResponse<Movie>
    suspend fun getRecommendedMovies(movieId: Int, page: Int): MovieResponse<Movie>
}
