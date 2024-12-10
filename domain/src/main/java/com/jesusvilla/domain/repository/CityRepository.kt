package com.jesusvilla.domain.repository

import com.jesusvilla.core.model.City
import com.jesusvilla.core.model.Resource
import kotlinx.coroutines.flow.Flow

/**
 * Created by Jesus Villa on 9/12/24.
 */
interface CityRepository {
    @Throws(Exception::class)
    suspend fun searchCities(id: String, uuid: String, resource: String): Flow<Resource<List<City>>>
}