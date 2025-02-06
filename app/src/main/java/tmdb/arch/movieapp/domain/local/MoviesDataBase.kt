package tmdb.arch.movieapp.domain.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import tmdb.arch.movieapp.domain.local.converters.StringListConverter
import tmdb.arch.movieapp.domain.model.local.FavoriteEntity
import tmdb.arch.movieapp.domain.model.local.ToWatchEntity

@Database(entities = [ToWatchEntity::class, FavoriteEntity::class], version = 1)
@TypeConverters(StringListConverter::class)
abstract class MoviesDataBase: RoomDatabase() {

    abstract val movies: MoviesDao
}