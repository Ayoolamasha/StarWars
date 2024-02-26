package com.ayoolamasha.starwars.features.presentation.featureCharacterDetails.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ayoolamasha.starwars.databinding.ItemsFilmsRecyclerDesignBinding
import com.ayoolamasha.starwars.domain.model.Films


class FilmsAdapter(private val onFilmClicked : (Films) -> Unit) :
    ListAdapter<Films, FilmsAdapter.FilmsViewHolder>(FilmsDiffCallBack) {

    inner class FilmsViewHolder(private val binding: ItemsFilmsRecyclerDesignBinding): RecyclerView.ViewHolder(binding.root){

        fun binds(items: Films){
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
    ): FilmsViewHolder {
        return FilmsViewHolder(
            ItemsFilmsRecyclerDesignBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FilmsViewHolder, position: Int) {
        val filmsUICase = getItem(position)
        holder.binds(filmsUICase)
    }

    object FilmsDiffCallBack : DiffUtil.ItemCallback<Films>() {
        override fun areItemsTheSame(
            oldItem: Films,
            newItem: Films
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: Films,
            newItem: Films
        ): Boolean {
            return false
        }

    }
}