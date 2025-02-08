package tmdb.arch.movieapp.repository.remote.model

import tmdb.arch.movieapp.repository.models.Movie

internal fun MovieDto.toModel(): Movie =
    Movie(
        adult = this.adult,
        backdropPath = this.backdropPath,
        genres = this.genres?.map(MovieDto.GenreDto::name),
        id = this.id,
        originalLanguage = this.originalLanguage,
        originalTitle = this.originalTitle,
        overview = this.overview,
        popularity = this.popularity,
        posterPath = this.posterPath,
        releaseDate = this.releaseDate,
        title = this.title,
        video = this.video,
        runtime = this.runtime,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount,
    )
