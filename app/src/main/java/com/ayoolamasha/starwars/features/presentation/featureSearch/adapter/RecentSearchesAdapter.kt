package com.ayoolamasha.starwars.features.presentation.featureSearch.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ayoolamasha.starwars.databinding.ItemsRecentSearchRecyclerDesignBinding
import com.ayoolamasha.starwars.domain.model.Characters

class RecentSearchesAdapter(private var onSearchEntityClicked: (Characters) -> Unit) :
    ListAdapter<Characters, RecentSearchesAdapter.RecentSearchViewHolder>(RecentSearchDiffCallBack) {

    inner class RecentSearchViewHolder(private val binding: ItemsRecentSearchRecyclerDesignBinding): RecyclerView.ViewHolder(binding.root){

        fun binds(items: Characters){
            binding.apply {
                characterUiCase = items
                executePendingBindings()
                setClickListener { onSearchEntityClicked(items) }
            }
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecentSearchViewHolder {
        return RecentSearchViewHolder(
            ItemsRecentSearchRecyclerDesignBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecentSearchViewHolder, position: Int) {
        val characterUICase = getItem(position)
        holder.binds(characterUICase)
    }

    object RecentSearchDiffCallBack : DiffUtil.ItemCallback<Characters>() {
        override fun areItemsTheSame(
            oldItem: Characters,
            newItem: Characters
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: Characters,
            newItem: Characters
        ): Boolean {
            return false
        }

    }
}