package com.ayoolamasha.starwars.core.extensions

import android.view.WindowManager
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.ayoolamasha.starwars.R
import com.google.android.material.snackbar.Snackbar

fun SearchView.queryTextListener(
    action: (String?) -> Unit,
    emptySearchView: () -> Unit,
) {


    setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            action(query)
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            if (newText.isNullOrBlank()) {
                emptySearchView.invoke()
            } else {
                action(newText)
            }
            return true
        }
    })
}


fun SearchView.closeListener(onCloseClick: () -> Unit) {
    setOnCloseListener {
        onCloseClick.invoke()
        true
    }
}

fun Fragment.statusBarColor() {
    val window = requireActivity().window
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
    window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.color_primary)
}

fun Fragment.statusBarColorWhite() {
    val window = requireActivity().window
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
    window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.white)
}

fun Fragment.showSnackBar(message: String) {
    Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT)
        .setAnimationMode(Snackbar.ANIMATION_MODE_SLIDE)
        .setBackgroundTint(ContextCompat.getColor(requireContext(), R.color.color_primary))
        .setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
        .show()

}

