package com.jesusvilla.core.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Jesus Villa on 9/12/24.
 */
data class City(
    @SerializedName("country")
    val country: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("_id")
    val id: Int? = null,
    @SerializedName("coord")
    val coordinates: Coordinates? = null,
)