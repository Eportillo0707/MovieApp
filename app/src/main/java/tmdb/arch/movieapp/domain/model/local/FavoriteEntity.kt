package tmdb.arch.movieapp.domain.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "favorites")
class FavoriteEntity(
    @PrimaryKey
    @ColumnInfo(name = "movie_id")
    val movieId: Long
)