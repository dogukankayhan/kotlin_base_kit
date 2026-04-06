package com.base.data.repository

import com.base.domain.repository.MovieRepository
import com.base.model.Movie
import com.base.model.MovieCreditResponse
import com.base.model.MovieDetail
import com.base.model.MovieResponse
import com.base.model.MovieReview
import com.base.model.MovieVideoResponse
import com.base.network.api.MovieApi
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api: MovieApi
) : MovieRepository {
    override suspend fun getPopularMovies(page: Int): MovieResponse<Movie> = api.getPopularMovies(page)
    override suspend fun getNowPlayingMovies(page: Int): MovieResponse<Movie> = api.getNowPlayingMovies(page)
    override suspend fun getTopRatedMovies(page: Int): MovieResponse<Movie> = api.getTopRatedMovies(page)
    override suspend fun getUpcomingMovies(page: Int): MovieResponse<Movie> = api.getUpcomingMovies(page)
    override suspend fun getTrendingMovies(): MovieResponse<Movie> = api.getTrendingMovies()
    override suspend fun searchMovies(query: String, page: Int): MovieResponse<Movie> = api.searchMovies(query, page)
    override suspend fun getMovieDetails(movieId: Int): MovieDetail = api.getMovieDetails(movieId)
    override suspend fun getMovieCredits(movieId: Int): MovieCreditResponse = api.getMovieCredits(movieId)
    override suspend fun getMovieVideos(movieId: Int): MovieVideoResponse = api.getMovieVideos(movieId)
    override suspend fun getMovieReviews(movieId: Int, page: Int): MovieResponse<MovieReview> = api.getMovieReviews(movieId, page)
    override suspend fun getSimilarMovies(movieId: Int, page: Int): MovieResponse<Movie> = api.getSimilarMovies(movieId, page)
    override suspend fun getRecommendedMovies(movieId: Int, page: Int): MovieResponse<Movie> = api.getRecommendedMovies(movieId, page)
}
