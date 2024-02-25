package com.ayoolamasha.starwars.featureSearch.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ayoolamasha.starwars.databinding.ItemsSearchResultRecyclerDesignBinding
import com.ayoolamasha.starwars.featureSearch.domain.model.CharacterUICase

class SearchAdapter(private var onSearchResultClick: (CharacterUICase) -> Unit) :
    ListAdapter<CharacterUICase, SearchAdapter.SearchViewHolder>(SearchDiffCallBack) {

    inner class SearchViewHolder(private val binding: ItemsSearchResultRecyclerDesignBinding): RecyclerView.ViewHolder(binding.root){

        fun binds(items: CharacterUICase){
            binding.apply {
                characterUI = items
                executePendingBindings()
                setClickListener { onSearchResultClick(items) }
            }
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchAdapter.SearchViewHolder {
        return SearchViewHolder(
            ItemsSearchResultRecyclerDesignBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SearchAdapter.SearchViewHolder, position: Int) {
        val characterUICase = getItem(position)
        holder.binds(characterUICase)
    }

    object SearchDiffCallBack : DiffUtil.ItemCallback<CharacterUICase>() {
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