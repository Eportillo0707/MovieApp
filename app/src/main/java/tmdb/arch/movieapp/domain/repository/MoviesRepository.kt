package tmdb.arch.movieapp.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import retrofit2.http.Query
import tmdb.arch.movieapp.domain.model.Movie
import tmdb.arch.movieapp.domain.model.remote.MovieDto
import tmdb.arch.movieapp.domain.model.remote.toModel
import tmdb.arch.movieapp.domain.remote.MoviesServices

class MoviesRepository(
    private val remoteService: MoviesServices,
) {
    fun getLatestMovies() = Pager(config = PagingConfig(pageSize = 50)) {
        LatestMoviesPagingSource(remoteService)
    }.flow

    suspend fun findMovies(query: String) = remoteService.findMovies(query)
        .movieDtos
        .map(MovieDto::toModel)

    suspend fun getMovieDetails(id: Long) = remoteService.getMovieDetails(id).toModel()
}
