package tmdb.arch.movieapp.ui.screens.discover.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tmdb.arch.movieapp.databinding.MoviesSavedListItemBinding
import viewBinding

class HeaderListAdapter(
    private val onFavoritesClicked: () -> Unit,
    private val onToWatchClicked: () -> Unit
) : RecyclerView.Adapter<HeaderListAdapter.HeaderViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderViewHolder =
        HeaderViewHolder(
            parent.viewBinding { layoutInflater, viewGroup, _ ->
                MoviesSavedListItemBinding.inflate(layoutInflater, viewGroup, false)
            }
        )

    override fun getItemCount() = 1

    override fun onBindViewHolder(holder: HeaderViewHolder, position: Int) = Unit

    inner class HeaderViewHolder(private val binding: MoviesSavedListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.favoritesButton.setOnClickListener { onFavoritesClicked() }
            binding.toWatchButton.setOnClickListener { onToWatchClicked() }

        }

    }

}