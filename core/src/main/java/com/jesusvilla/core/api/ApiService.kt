package com.jesusvilla.core.api

import com.jesusvilla.core.model.City
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Jesus Villa on 8/12/24.
 */
interface ApiService {
    // @GET: Retrieves data from the server
    @GET("/hernan-uala/{id}/raw/{uuid}/{resource}")
    suspend fun searchCities(
        @Path("id") id: String,
        @Path("uuid") uuid: String,
        @Path("resource") resource: String
    ): Response<List<City>>
}