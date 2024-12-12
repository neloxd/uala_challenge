package com.jesusvilla.ualachallenge.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
/**
 * Created by Jesus Villa on 10/12/24.
 */

@Parcelize
data class CoordinatesModel(
    val longitude: Double,
    val latitude: Double
) : Parcelable
