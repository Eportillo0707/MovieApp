package tmdb.arch.movieapp.repository.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import tmdb.arch.movieapp.repository.models.Movie

interface MoviesRepository {

    fun getLatestMovies(): Flow<PagingData<Movie>>

    suspend fun findMovies(query: String): List<Movie>

    suspend fun getMovieDetails(id: Long): Movie

    fun getFavoritesIds(): Flow<List<Long>>

    suspend fun insertFavorite(movieId: Long)

    suspend fun deleteFavorite(movieId: Long)

    fun getToWatchIds(): Flow<List<Long>>

    fun getToWatchMovies(): Flow<List<Movie>>

    suspend fun insertToWatch(movie: Movie)

    suspend fun deleteToWatch(movie: Movie)

}