// JsonUtils.kt
package com.capstone.ar_tourguide.utils

import android.content.Context
import com.capstone.ar_tourguide.model.Place
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStreamReader

object JsonUtils {

    fun loadPlacesFromJson(context: Context): List<Place> {
        val resourceId = context.resources.getIdentifier("destinasi", "raw", context.packageName)

        return try {
            val inputStream = context.resources.openRawResource(resourceId)
            Gson().fromJson(
                InputStreamReader(inputStream),
                object : TypeToken<List<Place>>() {}.type
            )
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
}
