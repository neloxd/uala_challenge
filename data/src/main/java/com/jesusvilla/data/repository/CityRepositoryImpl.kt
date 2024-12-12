package com.jesusvilla.data.repository

import android.util.Log
import com.jesusvilla.core.model.City
import com.jesusvilla.core.model.Resource
import com.jesusvilla.core.api.ApiService
import com.jesusvilla.core.di.ApiServiceInjection
import com.jesusvilla.domain.repository.CityRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

/**
 * Created by Jesus Villa on 9/12/24.
 */
class CityRepositoryImpl @Inject constructor(
    @ApiServiceInjection private val apiService: ApiService
): CityRepository {

    private val TAG = "CityRepositoryImpl"

    override suspend fun searchCities(
        id: String,
        uuid: String,
        resource: String
    ): Flow<Resource<List<City>>> = flow {
        try {
            val response = apiService.searchCities(
                id = id,
                uuid = uuid,
                resource = resource
            )
            //Log.i(TAG, "response:$response")
            if (response.isSuccessful) {
                emit(Resource.Success(response.body() ?: emptyList())) // Emit success state
            } else {
                emit(Resource.Error(message = "Error ${response.code()}: ${response.message()}")) // Emit error for unsuccessful response
            }
        } catch (exception: Exception) {
            emit(Resource.Error(message = "Failed to fetch cities: ${exception.message}")) // Emit error for caught exceptions
        }
    }.catch { exception ->
        // Handle any unexpected exceptions during Flow execution
        emit(Resource.Error(message = "An unexpected error occurred: ${exception.message}"))
    }
}