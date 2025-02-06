package tmdb.arch.movieapp.domain.usecases

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import tmdb.arch.movieapp.domain.model.Movie
import tmdb.arch.movieapp.domain.repository.MoviesRepository
import tmdb.arch.movieapp.utils.UiState

class GetMovieDetailsUseCase(private val repository: MoviesRepository) {

    operator fun invoke(id: Long): Flow<UiState<Movie>> = flow {
        emit(UiState.Loading)
        val result = repository.getMovieDetails(id)
        emit(UiState.Result(result))

        repository.getFavoritesIds()
            .combine(repository.getToWatchIds()) { favs, toWatch ->
                val isFaved = favs.any { movieId -> movieId == id }
                val isToWatch = toWatch.any { movieId -> movieId == id }

                return@combine UiState.Result(
                    result.copy(
                        isFavored = isFaved,
                        isToWatch = isToWatch
                    )
                )
            }.collect(::emit)

    }.catch {
        UiState.Error

    }


}