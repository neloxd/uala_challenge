package com.jesusvilla.ualachallenge.ui.model

/**
 * Created by Jesus Villa on 9/12/24.
 */
sealed class CityIntent {
    data object LoadCities : CityIntent()
    data class FilterCities(val query: String): CityIntent()
    data class NavigateMap(val cityModel: CityModel): CityIntent()
}