package tmdb.arch.movieapp.repository.remote.model

import com.google.gson.annotations.SerializedName

internal data class MovieListResponse(
    @SerializedName("page") val page: Int,
    @SerializedName("results")val movieDtos: List<MovieDto>,
    @SerializedName("total_pages")val totalPages: Int,
    @SerializedName("total_results")val totalResults: Int,
)
