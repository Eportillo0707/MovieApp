package tmdb.arch.movieapp.domain.usecases

import tmdb.arch.movieapp.repository.models.Movie
import tmdb.arch.movieapp.repository.repository.MoviesRepository

class UpdateSavedMoviesUseCase(
    private val repository: MoviesRepository,
) {
    suspend operator fun invoke(movie: Movie, command: Cmd) = when (command) {
        Cmd.TO_WATCH -> {
            if (movie.isToWatch) {
                repository.deleteToWatch(movie)

            } else {
                repository.insertToWatch(movie)
            }

        }

        Cmd.FAVORITE -> {
            if (movie.isFavored) {
                repository.deleteFavorite(movie.id)

            } else {
                repository.insertFavorite(movie.id)
            }

        }
    }

    enum class Cmd {
        TO_WATCH,
        FAVORITE

    }

}