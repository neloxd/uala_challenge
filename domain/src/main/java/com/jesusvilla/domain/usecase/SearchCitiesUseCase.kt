package com.jesusvilla.domain.usecase

import com.jesusvilla.core.model.City
import com.jesusvilla.core.model.Resource
import com.jesusvilla.domain.repository.CityRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Jesus Villa on 9/12/24.
 */

class SearchCitiesUseCase @Inject constructor(private val repository: CityRepository) {
    suspend operator fun invoke(): Flow<Resource<List<City>>> = repository.searchCities(ID, UUID, RESOURCE)
}

const val ID = "dce8843a8edbe0b0018b32e137bc2b3a"
const val UUID = "0996accf70cb0ca0e16f9a99e0ee185fafca7af1"
const val RESOURCE = "cities.json"