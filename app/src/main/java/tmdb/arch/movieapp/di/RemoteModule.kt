package tmdb.arch.movieapp.di

import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tmdb.arch.movieapp.domain.remote.AuthInterceptor
import tmdb.arch.movieapp.domain.remote.MoviesServices
import tmdb.arch.movieapp.domain.repository.MoviesRepository
import java.util.concurrent.TimeUnit

val remoteModule
    get() =
        module {
            single { MoviesRepository(moviesServices) }
        }

private val httpClient
    get() = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor())
            .callTimeout(30L, TimeUnit.SECONDS)
            .build()

private val retrofit
    get() =
        Retrofit
            .Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .baseUrl("https://api.themoviedb.org/3/")
            .build()
private val moviesServices get() = retrofit.create(MoviesServices::class.java)
