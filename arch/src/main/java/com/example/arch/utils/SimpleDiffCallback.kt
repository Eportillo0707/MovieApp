package com.example.arch.utils

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

class SimpleDiffCallback<T : Any> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean =
        oldItem::class == newItem::class

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean =
        oldItem == newItem
}