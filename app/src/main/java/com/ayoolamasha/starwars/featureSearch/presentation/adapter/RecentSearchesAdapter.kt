package com.ayoolamasha.starwars.featureSearch.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ayoolamasha.starwars.databinding.ItemsRecentSearchRecyclerDesignBinding
import com.ayoolamasha.starwars.featureSearch.domain.model.CharacterUICase

class RecentSearchesAdapter(private var onSearchEntityClicked: (CharacterUICase) -> Unit) :
    ListAdapter<CharacterUICase, RecentSearchesAdapter.RecentSearchViewHolder>(RecentSearchDiffCallBack) {

    inner class RecentSearchViewHolder(private val binding: ItemsRecentSearchRecyclerDesignBinding): RecyclerView.ViewHolder(binding.root){

        fun binds(items: CharacterUICase){
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
    ): RecentSearchesAdapter.RecentSearchViewHolder {
        return RecentSearchViewHolder(
            ItemsRecentSearchRecyclerDesignBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecentSearchesAdapter.RecentSearchViewHolder, position: Int) {
        val characterUICase = getItem(position)
        holder.binds(characterUICase)
    }

    object RecentSearchDiffCallBack : DiffUtil.ItemCallback<CharacterUICase>() {
        override fun areItemsTheSame(
            oldItem: CharacterUICase,
            newItem: CharacterUICase
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: CharacterUICase,
            newItem: CharacterUICase
        ): Boolean {
            return false
        }

    }
}