package tmdb.arch.movieapp.repository.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "to_watch")
internal class ToWatchEntity (
    @PrimaryKey
    @ColumnInfo(name = "movie_id")
    val movieId: Long,
    @ColumnInfo(name = "title")
    val title: String?,
    @ColumnInfo(name = "original_title")
    val originalTitle: String?,
    @ColumnInfo(name = "release_date")
    val releaseDate: String?,
    @ColumnInfo(name = "vote_average")
    val voteAverage: String?,
    @ColumnInfo(name = "poster_path")
    val posterPath: String?,
    @ColumnInfo(name = "overview")
    val overview: String?,
    @ColumnInfo(name = "genres")
    val genres: List<String>?,
    @ColumnInfo(name = "runtime")
    val runtime: Int?




)