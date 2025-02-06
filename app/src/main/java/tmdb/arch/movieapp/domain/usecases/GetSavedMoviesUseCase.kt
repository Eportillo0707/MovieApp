package tmdb.arch.movieapp.domain.usecases

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import tmdb.arch.movieapp.domain.model.Movie
import tmdb.arch.movieapp.domain.repository.MoviesRepository

class GetSavedMoviesUseCase(
    private val repository: MoviesRepository

) {
    operator fun invoke(command: Cmd): Flow<List<Movie>> = when (command) {
        Cmd.TO_WATCH -> repository.getToWatchMovies()
        Cmd.FAVORITES -> getFavorites()
    }
        .catch { emit(emptyList()) }

    private fun getFavorites(): Flow<List<Movie>> = repository.getFavoritesIds()
        .map { list ->
            coroutineScope {
                list.map { id ->
                    async {
                        repository.getMovieDetails(id)
                    }
                }.awaitAll()

            }

        }


    enum class Cmd {
        TO_WATCH,
        FAVORITES

    }

}