package com.capstone.ar_tourguide.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class cafe (
    val name: String,
    val address: String,
    val phone: String,
    val openingHours: String,
    val image: String,
): Parcelable