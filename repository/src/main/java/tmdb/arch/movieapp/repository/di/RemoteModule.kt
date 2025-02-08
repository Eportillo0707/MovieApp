package tmdb.arch.movieapp.repository.di

import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tmdb.arch.movieapp.repository.local.MoviesDao
import tmdb.arch.movieapp.repository.remote.MoviesServices
import tmdb.arch.movieapp.repository.remote.interceptors.AuthInterceptor
import tmdb.arch.movieapp.repository.repository.MoviesRepository
import java.util.concurrent.TimeUnit



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
internal val moviesServices get() = retrofit.create(MoviesServices::class.java)
