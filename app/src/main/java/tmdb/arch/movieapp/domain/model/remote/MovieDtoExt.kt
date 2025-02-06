package tmdb.arch.movieapp.domain.model.remote

import tmdb.arch.movieapp.domain.model.Movie

fun MovieDto.toModel(): Movie =
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
