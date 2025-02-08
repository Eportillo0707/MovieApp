package tmdb.arch.movieapp.ui.common

import androidx.recyclerview.widget.RecyclerView
import coil.load
import tmdb.arch.movieapp.BuildConfig
import tmdb.arch.movieapp.databinding.MovieListItemBinding
import tmdb.arch.movieapp.repository.models.Movie

class MovieViewHolder(
    private val binding: MovieListItemBinding,
    private val onClick: (Long) -> Unit
) : RecyclerView.ViewHolder(binding.root) {



    fun bind(item: Movie) {
        binding.title.text = item.title
        binding.originalTitle.text = item.originalTitle
        binding.releaseDate.text = item.releaseDate
        binding.poster.load(BuildConfig.IMAGE_URL + item.posterPath)

        binding.root.setOnClickListener {
            onClick(item.id)
        }

    }
}