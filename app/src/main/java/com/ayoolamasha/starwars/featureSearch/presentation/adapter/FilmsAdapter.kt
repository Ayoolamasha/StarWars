package com.ayoolamasha.starwars.featureSearch.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ayoolamasha.starwars.databinding.ItemsFilmsRecyclerDesignBinding
import com.ayoolamasha.starwars.featureSearch.domain.model.FilmsUiCase

class FilmsAdapter(private val onFilmClicked : (FilmsUiCase) -> Unit) :
    ListAdapter<FilmsUiCase, FilmsAdapter.FilmsViewHolder>(FilmsDiffCallBack) {

    inner class FilmsViewHolder(private val binding: ItemsFilmsRecyclerDesignBinding): RecyclerView.ViewHolder(binding.root){

        fun binds(items: FilmsUiCase){
            binding.apply {
                filmsUI = items
                executePendingBindings()
                setClickListener { onFilmClicked(items) }
            }
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FilmsAdapter.FilmsViewHolder {
        return FilmsViewHolder(
            ItemsFilmsRecyclerDesignBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FilmsAdapter.FilmsViewHolder, position: Int) {
        val filmsUICase = getItem(position)
        holder.binds(filmsUICase)
    }

    object FilmsDiffCallBack : DiffUtil.ItemCallback<FilmsUiCase>() {
        override fun areItemsTheSame(
            oldItem: FilmsUiCase,
            newItem: FilmsUiCase
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: FilmsUiCase,
            newItem: FilmsUiCase
        ): Boolean {
            return false
        }

    }
}