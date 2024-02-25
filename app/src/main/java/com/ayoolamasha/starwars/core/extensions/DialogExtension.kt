package com.ayoolamasha.starwars.core.extensions

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.ayoolamasha.starwars.R
import com.ayoolamasha.starwars.databinding.LayoutDeleteAllCharactersDialogBinding

fun Fragment.openDeleteAllCharactersPopUpDialog(
    deleteAllCharacters: () -> Unit,
) {

    lateinit var dialog: AlertDialog
    val dialogBuilder = AlertDialog.Builder(requireContext(), R.style.Theme_AlertDialog)

    val layoutInflater =
        (requireContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)

    val binding = LayoutDeleteAllCharactersDialogBinding.inflate(layoutInflater, null, false)



    binding.continueButton.setOnClickListener {
        dialog.dismiss()
        deleteAllCharacters.invoke()

    }


    binding.closeButton.setOnClickListener { dialog.dismiss() }



    dialogBuilder.setView(binding.root)

    dialogBuilder.setCancelable(true)

    dialog = dialogBuilder.create()

    dialog.show()
}