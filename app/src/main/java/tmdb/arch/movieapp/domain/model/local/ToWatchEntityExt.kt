package tmdb.arch.movieapp.domain.model.local

import tmdb.arch.movieapp.domain.model.Movie

fun ToWatchEntity.toModel(): Movie = Movie(
    genres = this.genres,
    id = this.movieId,
    originalTitle = this.originalTitle,
    title = this.title,
    overview = this.overview,
    posterPath = this.posterPath

)

fun Movie.toToWatchEntity(): ToWatchEntity = ToWatchEntity(
    movieId = this.id,
    title = this.title,
    originalTitle = this.originalTitle,
    releaseDate = this.releaseDate,
    voteAverage = this.voteAverage,
    posterPath = this.posterPath,
    overview = this.overview,
    genres = this.genres,
    runtime = this.runtime
)