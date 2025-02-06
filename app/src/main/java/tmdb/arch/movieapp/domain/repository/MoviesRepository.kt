package tmdb.arch.movieapp.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import retrofit2.http.Query
import tmdb.arch.movieapp.domain.local.MoviesDao
import tmdb.arch.movieapp.domain.model.Movie
import tmdb.arch.movieapp.domain.model.local.FavoriteEntity
import tmdb.arch.movieapp.domain.model.local.ToWatchEntity
import tmdb.arch.movieapp.domain.model.local.toModel
import tmdb.arch.movieapp.domain.model.local.toToWatchEntity
import tmdb.arch.movieapp.domain.model.remote.MovieDto
import tmdb.arch.movieapp.domain.model.remote.toModel
import tmdb.arch.movieapp.domain.remote.MoviesServices

class MoviesRepository(
    private val remoteService: MoviesServices,
    private val moviesDao: MoviesDao
) {
    fun getLatestMovies() = Pager(config = PagingConfig(pageSize = 50)) {
        LatestMoviesPagingSource(remoteService)
    }.flow

    suspend fun findMovies(query: String) = remoteService.findMovies(query)
        .movieDtos
        .map(MovieDto::toModel)

    suspend fun getMovieDetails(id: Long) = remoteService.getMovieDetails(id).toModel()

    fun getFavoritesIds(): Flow<List<Long>> = moviesDao.getAllFavorites()
        .map { items -> items.map { entity -> entity.movieId } }

    suspend fun insertFavorite(movieId: Long) =
        moviesDao.insertFavorite(FavoriteEntity(movieId))

    suspend fun deleteFavorite(movieId: Long) =
        moviesDao.deleteFavorite(FavoriteEntity(movieId))

    fun getToWatchIds(): Flow<List<Long>> = moviesDao.getAllToWatch()
        .map { items -> items.map { entity -> entity.movieId } }

    fun getToWatchMovies(): Flow<List<Movie>> = moviesDao.getAllToWatch()
        .map { list ->
            list.map(ToWatchEntity::toModel)
        }

    suspend fun insertToWatch(movie: Movie) =
        moviesDao.insertToWatch(movie.toToWatchEntity())

    suspend fun deleteToWatch(movie: Movie) =
        moviesDao.deleteToWatch(movie.id)


}
