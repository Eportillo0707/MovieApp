package tmdb.arch.movieapp.ui.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow

import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import tmdb.arch.movieapp.domain.model.Movie
import tmdb.arch.movieapp.domain.usecases.GetMovieDetailsUseCase
import tmdb.arch.movieapp.domain.usecases.UpdateSavedMoviesUseCase
import tmdb.arch.movieapp.utils.UiState

class MovieDetailsViewModel(
    private val movieId: Long,
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val updateSavedMoviesUseCase: UpdateSavedMoviesUseCase
) : ViewModel() {

    private val _movie: MutableStateFlow<UiState<Movie>> = MutableStateFlow(UiState.Loading)

    val movie: StateFlow<UiState<Movie>> get() = _movie.asStateFlow()

    init {
        loadMovie()

    }

    private fun loadMovie() {
        viewModelScope.launch {
            _movie.value = UiState.Error
            getMovieDetailsUseCase(movieId)
                .collect(_movie)
        }
    }

    fun onRetryClicked() = loadMovie()

    fun onToWatchButtonClicked() = updateSavedMovie(UpdateSavedMoviesUseCase.Cmd.TO_WATCH)

    fun onFavoriteButtonClicked() = updateSavedMovie(UpdateSavedMoviesUseCase.Cmd.FAVORITE)

    private fun updateSavedMovie(cmd: UpdateSavedMoviesUseCase.Cmd) = viewModelScope.launch {
        val currentValue = movie.value
        if (currentValue is UiState.Result) {
            val item = currentValue.item
            updateSavedMoviesUseCase(item, cmd)
        }
    }
}
