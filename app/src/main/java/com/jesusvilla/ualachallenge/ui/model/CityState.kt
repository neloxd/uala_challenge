package com.jesusvilla.ualachallenge.ui.model

import com.jesusvilla.core.model.City

/**
 * Created by Jesus Villa on 9/12/24.
 */
data class CityState (
    val isLoading: Boolean = false,

    val cities: List<City>? = null,

    val errorMessage: String? = null
)