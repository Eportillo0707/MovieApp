package tmdb.arch.movieapp.utils

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import tmdb.arch.movieapp.domain.model.Movie

class SimpleDiffCallback<T : Any> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean =
        oldItem::class == newItem::class

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean =
        oldItem == newItem
}