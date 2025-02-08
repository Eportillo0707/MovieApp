package tmdb.arch.movieapp.repository.di

import android.content.Context
import org.koin.dsl.module
import tmdb.arch.movieapp.repository.repository.MoviesRepository
import tmdb.arch.movieapp.repository.repository.MoviesRepositoryImpl

val repositoryModule
    get() =
        module {
            single<MoviesRepository> {
                MoviesRepositoryImpl(
                    remoteService = moviesServices,
                    moviesDao = createMoviesDataBase(context = get<Context>())
                )
            }
        }

