package tmdb.arch.movieapp.domain.usecases

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import tmdb.arch.movieapp.repository.models.Movie
import tmdb.arch.movieapp.repository.repository.MoviesRepository

class GetLatestMoviesUseCase(private val repository: MoviesRepository) {
    operator fun invoke(): Flow<PagingData<Movie>> =  repository.getLatestMovies()
}