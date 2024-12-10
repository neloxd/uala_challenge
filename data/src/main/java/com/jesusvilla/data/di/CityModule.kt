package com.jesusvilla.data.di

import com.jesusvilla.core.api.ApiService
import com.jesusvilla.core.di.ApiServiceInjection
import com.jesusvilla.core.di.CityRepositoryInjection
import com.jesusvilla.core.di.SearchCitiesUseCaseInjection
import com.jesusvilla.data.repository.CityRepositoryImpl
import com.jesusvilla.domain.repository.CityRepository
import com.jesusvilla.domain.usecase.SearchCitiesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
class CityModule {

    @Provides
    @CityRepositoryInjection
    fun provideCityRepository(
        @ApiServiceInjection apiService: ApiService
    ): CityRepository {
        return CityRepositoryImpl(apiService)
    }

    @Provides
    @SearchCitiesUseCaseInjection
    fun provideSearchCitiesUseCase(
        @CityRepositoryInjection cityRepository: CityRepository
    ): SearchCitiesUseCase {
        return SearchCitiesUseCase(cityRepository)
    }
}