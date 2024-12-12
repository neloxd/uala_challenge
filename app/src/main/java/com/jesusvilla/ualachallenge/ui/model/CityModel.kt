package com.jesusvilla.ualachallenge.ui.model

import android.os.Parcelable
import com.jesusvilla.core.model.City
import kotlinx.parcelize.Parcelize


/**
 * Created by Jesus Villa on 10/12/24.
 */
@Parcelize
data class CityModel(
    val country: String,
    val name: String,
    val id: Int,
    val coordinates: CoordinatesModel,
    var isSelected: Boolean = false
): Parcelable

fun City.mapToCityModel(): CityModel {
    return CityModel(
        country = this.country.toString(),
        name = this.name.toString(),
        id = this.id.toString().toInt(),
        coordinates = CoordinatesModel(
            longitude = this.coordinates?.longitude ?: Double.NaN,
            latitude = this.coordinates?.latitude ?: Double.NaN
        ),
    )
}

fun List<City>.mapToModelUi(): List<CityModel> {
    return this.map {
        it.mapToCityModel()
    }.toList()
}