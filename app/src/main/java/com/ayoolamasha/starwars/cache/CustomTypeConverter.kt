package com.ayoolamasha.starwars.cache

import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class CustomTypeConverter {
    private val moshi: Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val listType = Types.newParameterizedType(List::class.java, String::class.java)
    private val listAdapter = moshi.adapter<List<String>>(listType)


    @TypeConverter
    fun fromListToJsonString(value: List<String>): String = listAdapter.toJson(value)

    @TypeConverter
    fun fromJsonStringToList(value: String) = listAdapter.fromJson(value).orEmpty()
}