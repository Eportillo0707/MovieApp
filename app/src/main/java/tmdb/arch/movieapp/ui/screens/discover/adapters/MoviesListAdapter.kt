package tmdb.arch.movieapp.ui.screens.discover.adapters

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import tmdb.arch.movieapp.databinding.MovieListItemBinding
import tmdb.arch.movieapp.repository.models.Movie
import com.example.arch.utils.SimpleDiffCallback
import viewBinding
import tmdb.arch.movieapp.ui.common.MovieViewHolder

class MoviesListAdapter(
    private val onMovieClicked: (Long) -> Unit
) :
    PagingDataAdapter<Movie, MovieViewHolder>(SimpleDiffCallback()) {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): MovieViewHolder =
        MovieViewHolder(
            binding =
            parent.viewBinding { layoutInflater, viewGroup, _ ->
                MovieListItemBinding.inflate(layoutInflater, viewGroup, false)
            },
            onClick = onMovieClicked
        )

    override fun onBindViewHolder(
        holder: MovieViewHolder,
        position: Int,
    ) {
        val item = getItem(position) ?: return

        holder.bind(item)

    }


}
