package com.jesusvilla.core.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Jesus Villa on 8/12/24.
 */
data class Coordinates(
    @SerializedName("lon")
    val longitude: Double? = null,
    @SerializedName("lat")
    val latitude: Double? = null
)