package tmdb.arch.movieapp.domain.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import tmdb.arch.movieapp.domain.model.Movie
import tmdb.arch.movieapp.domain.model.remote.MovieDto
import tmdb.arch.movieapp.domain.model.remote.toModel
import tmdb.arch.movieapp.domain.remote.MoviesServices

class LatestMoviesPagingSource(
    private val service: MoviesServices
) : PagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? = 1

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val key = params.key ?: 1
        return try {
            val response = service.getLatestMovies(key)
                .movieDtos
                .map(MovieDto::toModel)
            LoadResult.Page(
                data = response,
                prevKey = null,
                nextKey = key + 1
            )
        } catch (ex: Exception) {
            LoadResult.Error(ex)
        }
    }
}