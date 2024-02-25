package com.ayoolamasha.starwars.featureSearch.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ayoolamasha.starwars.featureSearch.presentation.ui.CharacterFilmsFragment
import com.ayoolamasha.starwars.featureSearch.presentation.ui.CharacterProfileFragment

const val CHARACTER_PROFILE_FRAGMENT = 0
const val CHARACTER_FILMS_FRAGMENT = 1

class CharacterDetailsViewHolderAdapter (fragment: Fragment) :
    FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> CharacterProfileFragment()
            1 -> CharacterFilmsFragment()
            else -> CharacterProfileFragment()

        }
    }
}