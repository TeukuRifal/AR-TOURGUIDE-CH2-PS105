// Place.kt
package com.capstone.ar_tourguide.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Place(
    val id: Int,
    val name: String,
    val description: String,
    val photo: String,
    val history: String,
    val funFacts: String,
    val latitude: Double,
    val longitude: Double
) : Parcelable
