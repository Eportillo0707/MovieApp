package tmdb.arch.movieapp.domain.usecases

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import tmdb.arch.movieapp.repository.models.Movie
import tmdb.arch.movieapp.repository.repository.MoviesRepository
import com.example.arch.utils.UiState

class GetMovieDetailsUseCase(private val repository: MoviesRepository) {

    operator fun invoke(id: Long): Flow<com.example.arch.utils.UiState<Movie>> = flow {
        emit(com.example.arch.utils.UiState.Loading)
        val result = repository.getMovieDetails(id)
        emit(com.example.arch.utils.UiState.Result(result))

        repository.getFavoritesIds()
            .combine(repository.getToWatchIds()) { favs, toWatch ->
                val isFaved = favs.any { movieId -> movieId == id }
                val isToWatch = toWatch.any { movieId -> movieId == id }

                return@combine com.example.arch.utils.UiState.Result(
                    result.copy(
                        isFavored = isFaved,
                        isToWatch = isToWatch
                    )
                )
            }.collect(::emit)

    }.catch {
        com.example.arch.utils.UiState.Error

    }


}