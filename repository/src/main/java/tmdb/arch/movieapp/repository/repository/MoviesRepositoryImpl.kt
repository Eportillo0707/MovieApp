package tmdb.arch.movieapp.repository.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import tmdb.arch.movieapp.repository.local.MoviesDao
import tmdb.arch.movieapp.repository.local.model.FavoriteEntity
import tmdb.arch.movieapp.repository.local.model.ToWatchEntity
import tmdb.arch.movieapp.repository.local.model.toModel
import tmdb.arch.movieapp.repository.local.model.toToWatchEntity
import tmdb.arch.movieapp.repository.models.Movie
import tmdb.arch.movieapp.repository.remote.MoviesServices
import tmdb.arch.movieapp.repository.remote.model.MovieDto
import tmdb.arch.movieapp.repository.remote.model.toModel
import tmdb.arch.movieapp.repository.repository.source.LatestMoviesPagingSource

internal class MoviesRepositoryImpl(
    private val remoteService: MoviesServices,
    private val moviesDao: MoviesDao
) : MoviesRepository {
    override fun getLatestMovies() = Pager(config = PagingConfig(pageSize = 50)) {
        LatestMoviesPagingSource(remoteService)
    }.flow

    override suspend fun findMovies(query: String) = remoteService.findMovies(query)
        .movieDtos
        .map(MovieDto::toModel)

    override suspend fun getMovieDetails(id: Long) = remoteService.getMovieDetails(id).toModel()

    override fun getFavoritesIds(): Flow<List<Long>> = moviesDao.getAllFavorites()
        .map { items -> items.map { entity -> entity.movieId } }

    override suspend fun insertFavorite(movieId: Long) =
        moviesDao.insertFavorite(FavoriteEntity(movieId))

    override suspend fun deleteFavorite(movieId: Long) =
        moviesDao.deleteFavorite(FavoriteEntity(movieId))

    override fun getToWatchIds(): Flow<List<Long>> = moviesDao.getAllToWatch()
        .map { items -> items.map { entity -> entity.movieId } }

    override fun getToWatchMovies(): Flow<List<Movie>> = moviesDao.getAllToWatch()
        .map { list ->
            list.map(ToWatchEntity::toModel)
        }

    override suspend fun insertToWatch(movie: Movie) =
        moviesDao.insertToWatch(movie.toToWatchEntity())

    override suspend fun deleteToWatch(movie: Movie) =
        moviesDao.deleteToWatch(movie.id)


}