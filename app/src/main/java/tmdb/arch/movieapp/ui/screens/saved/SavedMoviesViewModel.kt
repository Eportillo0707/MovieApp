package tmdb.arch.movieapp.ui.screens.saved

import androidx.lifecycle.ViewModel
import tmdb.arch.movieapp.domain.usecases.GetSavedMoviesUseCase

class SavedMoviesViewModel(
    private val command: GetSavedMoviesUseCase.Cmd,
    private val getSavedMoviesUseCase: GetSavedMoviesUseCase
) : ViewModel(){
    val savedMovies get() = getSavedMoviesUseCase(command)
}
