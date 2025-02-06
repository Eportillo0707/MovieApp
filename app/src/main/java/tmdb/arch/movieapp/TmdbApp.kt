package tmdb.arch.movieapp

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import tmdb.arch.movieapp.di.localModule
import tmdb.arch.movieapp.di.remoteModule
import tmdb.arch.movieapp.di.usecases
import tmdb.arch.movieapp.di.viewModels

class TmdbApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(androidContext = applicationContext)
            modules(
                viewModels,
                usecases,
                remoteModule,
                localModule
            )
        }
    }
}
