package com.ayoolamasha.starwars.featureSearch.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.ayoolamasha.starwars.R
import com.ayoolamasha.starwars.core.extensions.statusBarColor
import com.ayoolamasha.starwars.core.extensions.statusBarColorWhite
import com.ayoolamasha.starwars.databinding.FragmentSearchDetailsPagerBinding
import com.ayoolamasha.starwars.featureSearch.presentation.adapter.CHARACTER_FILMS_FRAGMENT
import com.ayoolamasha.starwars.featureSearch.presentation.adapter.CHARACTER_PROFILE_FRAGMENT
import com.ayoolamasha.starwars.featureSearch.presentation.adapter.CharacterDetailsViewHolderAdapter
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailsViewHolderFragment : Fragment(){
    private lateinit var binding: FragmentSearchDetailsPagerBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentSearchDetailsPagerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        statusBarColorWhite()
        binding.backArrow.setOnClickListener { binding.root.findNavController().navigate(R.id.action_characterDetailsViewHolderFragment_to_characterSearchFragment) }

        val viewPager = binding.viewPager
        val tabLayout = binding.actionTabs
        viewPager.adapter = CharacterDetailsViewHolderAdapter(this)
        viewPager.isUserInputEnabled = false

        TabLayoutMediator(tabLayout, viewPager) { tabs, position ->
            tabs.text = getTabTitles(position)
        }.attach()

    }


    private fun getTabTitles(position: Int): String? {
        return when (position) {
            CHARACTER_PROFILE_FRAGMENT -> getString(R.string.profile)
            CHARACTER_FILMS_FRAGMENT -> getString(R.string.films)
            else -> null
        }
    }
}