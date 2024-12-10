package com.jesusvilla.core.model

/**
 * Created by Jesus Villa on 9/12/24.
 */
sealed class Resource<T> {
    data class Success<T>(val data: T) : Resource<T>()
    data class Error<T>(val message: String, val data: T? = null) : Resource<T>()
    class Loading<T> : Resource<T>()
}