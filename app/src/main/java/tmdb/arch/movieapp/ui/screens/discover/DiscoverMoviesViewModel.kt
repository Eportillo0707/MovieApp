package tmdb.arch.movieapp.ui.screens.discover

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import tmdb.arch.movieapp.repository.models.Movie
import tmdb.arch.movieapp.domain.usecases.GetLatestMoviesUseCase

class DiscoverMoviesViewModel(
    getLatestMoviesUseCase: GetLatestMoviesUseCase
) : ViewModel() {

    private val _movies = MutableStateFlow<PagingData<Movie>>(PagingData.empty())
    val movies: StateFlow<PagingData<Movie>> get() = _movies.asStateFlow()

    init {
        viewModelScope.launch {
            getLatestMoviesUseCase()
                .cachedIn(viewModelScope)
                .collectLatest(_movies::emit)


        }
    }
}
