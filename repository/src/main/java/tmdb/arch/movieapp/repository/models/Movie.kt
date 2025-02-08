package tmdb.arch.movieapp.repository.models

data class Movie(
    val adult: Boolean? = null,
    val backdropPath: String? = null,
    val genres: List<String>?,
    val id: Long,
    val originalLanguage: String? = null,
    val originalTitle: String?,
    val overview: String? = null,
    val runtime: Int? = null,
    val popularity: Double? = null,
    val posterPath: String? = null,
    val releaseDate: String? = null,
    val title: String,
    val video: Boolean? = null,
    val voteAverage: String? = null,
    val voteCount: Int? = null,
    var isFavored: Boolean = false,
    var isToWatch: Boolean = false
)
