package com.jesusvilla.core.di

import javax.inject.Qualifier

/**
 * Created by Jesus Villa on 8/12/24.
 */

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class LoggingInterceptor

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class HeaderInterceptor

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class AuthOkHttpClient

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class AuthRetrofit

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ApiServiceInjection

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class GsonInjection

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class CityRepositoryInjection

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class SearchCitiesUseCaseInjection