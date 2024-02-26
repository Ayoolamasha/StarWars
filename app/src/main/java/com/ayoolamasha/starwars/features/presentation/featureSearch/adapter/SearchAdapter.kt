package com.ayoolamasha.starwars.features.presentation.featureSearch.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ayoolamasha.starwars.databinding.ItemsSearchResultRecyclerDesignBinding
import com.ayoolamasha.starwars.domain.model.Characters

class SearchAdapter(private var onSearchResultClick: (Characters) -> Unit) :
    ListAdapter<Characters, SearchAdapter.SearchViewHolder>(SearchDiffCallBack) {

    inner class SearchViewHolder(private val binding: ItemsSearchResultRecyclerDesignBinding): RecyclerView.ViewHolder(binding.root){

        fun binds(items: Characters){
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
    ): SearchViewHolder {
        return SearchViewHolder(
            ItemsSearchResultRecyclerDesignBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val characterUICase = getItem(position)
        holder.binds(characterUICase)
    }

    object SearchDiffCallBack : DiffUtil.ItemCallback<Characters>() {
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