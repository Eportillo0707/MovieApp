package tmdb.arch.movieapp.repository.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import tmdb.arch.movieapp.repository.local.converters.StringListConverter
import tmdb.arch.movieapp.repository.local.model.FavoriteEntity
import tmdb.arch.movieapp.repository.local.model.ToWatchEntity

@Database(entities = [ToWatchEntity::class, FavoriteEntity::class], version = 1)
@TypeConverters(StringListConverter::class)
internal abstract class MoviesDataBase : RoomDatabase() {

    abstract val movies: MoviesDao
}