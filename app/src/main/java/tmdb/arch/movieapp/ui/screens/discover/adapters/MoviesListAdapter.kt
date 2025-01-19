package tmdb.arch.movieapp.ui.screens.discover.adapters

import android.view.MenuItem
import android.view.ViewGroup
import androidx.paging.PagingData
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import tmdb.arch.movieapp.BuildConfig
import tmdb.arch.movieapp.databinding.MovieListItemBinding
import tmdb.arch.movieapp.domain.model.Movie
import tmdb.arch.movieapp.utils.SimpleDiffCallback
import viewBinding
import com.bumptech.glide.Glide
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
