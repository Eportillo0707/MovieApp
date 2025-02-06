package tmdb.arch.movieapp.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import tmdb.arch.movieapp.domain.repository.MoviesRepository
import tmdb.arch.movieapp.domain.usecases.GetLatestMoviesUseCase
import tmdb.arch.movieapp.domain.usecases.GetMovieDetailsUseCase
import tmdb.arch.movieapp.domain.usecases.GetSavedMoviesUseCase
import tmdb.arch.movieapp.domain.usecases.MoviesSearchInteractor
import tmdb.arch.movieapp.domain.usecases.UpdateSavedMoviesUseCase
import tmdb.arch.movieapp.ui.screens.details.MovieDetailsViewModel
import tmdb.arch.movieapp.ui.screens.discover.DiscoverMoviesViewModel
import tmdb.arch.movieapp.ui.screens.saved.SavedMoviesViewModel
import tmdb.arch.movieapp.ui.screens.search.SearchMoviesViewModel

val viewModels =
    module {
        viewModel { DiscoverMoviesViewModel(getLatestMoviesUseCase = get<GetLatestMoviesUseCase>()) }
        viewModel {
            MovieDetailsViewModel(
                movieId = it.get<Long>(),
                getMovieDetailsUseCase = get<GetMovieDetailsUseCase>(),
                updateSavedMoviesUseCase = get<UpdateSavedMoviesUseCase>()
            )
        }
        viewModel {
            SavedMoviesViewModel(
                command = it.get<GetSavedMoviesUseCase.Cmd>(),
                getSavedMoviesUseCase = get<GetSavedMoviesUseCase>()
            )
        }
        viewModel { SearchMoviesViewModel(searchInteractor = get<MoviesSearchInteractor>()) }
    }
val usecases = module {
    factory { GetLatestMoviesUseCase(repository = get<MoviesRepository>()) }
    factory { MoviesSearchInteractor(repository = get<MoviesRepository>()) }
    factory { GetMovieDetailsUseCase(repository = get<MoviesRepository>()) }
    factory { UpdateSavedMoviesUseCase(repository = get<MoviesRepository>()) }
    factory { GetSavedMoviesUseCase(repository = get<MoviesRepository>()) }
}