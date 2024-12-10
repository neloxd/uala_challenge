package com.jesusvilla.core.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Jesus Villa on 8/12/24.
 */
data class Coordinates(
    @SerializedName("lon")
    val longitude: Float? = null,
    @SerializedName("lat")
    val latitude: Float? = null
)