package tmdb.arch.movieapp.ui.screens.saved.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import tmdb.arch.movieapp.databinding.MovieListItemBinding
import tmdb.arch.movieapp.repository.models.Movie
import tmdb.arch.movieapp.ui.common.MovieViewHolder
import com.example.arch.utils.SimpleDiffCallback
import viewBinding

class SavedMoviesAdapter(
    private val onMovieClicked: (Long) -> Unit
): ListAdapter<Movie, MovieViewHolder>(SimpleDiffCallback<Movie>()) {
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