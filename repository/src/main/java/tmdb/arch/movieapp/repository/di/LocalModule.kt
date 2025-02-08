package tmdb.arch.movieapp.repository.di

import android.content.Context
import androidx.room.Room
import tmdb.arch.movieapp.repository.local.MoviesDataBase
import tmdb.arch.movieapp.repository.local.converters.StringListConverter


internal fun createMoviesDataBase(context: Context) =
    Room.databaseBuilder(context, MoviesDataBase::class.java, "movies_db")
        .addTypeConverter(StringListConverter())
        .build()
        .movies