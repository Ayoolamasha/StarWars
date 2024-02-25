package com.ayoolamasha.starwars.core.uiUtils

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.ayoolamasha.starwars.core.INCHES_MULTIPLIER
import com.ayoolamasha.starwars.core.UNKNOWN
import kotlin.math.ceil

@BindingAdapter("app:heightConverter")
fun convertHeightToCMAndInches(textView: TextView, height: String?){
    if (height != null && height != UNKNOWN){
        textView.text = heightStringBuilder(height = height, inches = heightToInches(height = height))
    }else{
        textView.text = height
    }

}

fun heightStringBuilder(height: String, inches:Double): String {
    val stringBuilder = StringBuilder()
    stringBuilder.append("$height CM, $inches INCHES")

    return stringBuilder.toString()
}

fun heightToInches(height: String): Double{
    val inchesMultiply = height.toInt() * INCHES_MULTIPLIER
    return ceil(inchesMultiply)
}
