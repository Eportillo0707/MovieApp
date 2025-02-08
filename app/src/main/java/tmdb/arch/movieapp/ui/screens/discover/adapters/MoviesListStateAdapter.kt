package tmdb.arch.movieapp.ui.screens.discover.adapters

import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import tmdb.arch.movieapp.databinding.MovieStateListItemBinding
import viewBinding

class MoviesListStateAdapter (
    private val retryListener: () -> Unit
):
    LoadStateAdapter<MoviesListStateAdapter.MoviesListStateViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): MoviesListStateViewHolder = MoviesListStateViewHolder( parent.viewBinding { layoutInflater, viewGroup, _ ->
        MovieStateListItemBinding.inflate(layoutInflater, viewGroup, false)
    }

    )

    override fun onBindViewHolder(holder: MoviesListStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }


    inner class MoviesListStateViewHolder(private val binding: MovieStateListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
            init {
                binding.retryButton.setOnClickListener { retryListener() }
            }

        fun bind(loadState: LoadState){
            binding.loadingIndicator.isVisible = loadState is LoadState.Loading
            binding.error.isVisible = loadState is LoadState.Error

        }
    }
}