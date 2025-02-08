package tmdb.arch.movieapp.repository.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "favorites")
internal class FavoriteEntity(
    @PrimaryKey
    @ColumnInfo(name = "movie_id")
    val movieId: Long
)