package com.jesusvilla.ualachallenge.ui.viewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jesusvilla.core.di.SearchCitiesUseCaseInjection
import com.jesusvilla.core.model.Resource
import com.jesusvilla.domain.usecase.SearchCitiesUseCase
import com.jesusvilla.ualachallenge.ui.model.CityIntent
import com.jesusvilla.ualachallenge.ui.model.CityState
import com.jesusvilla.ualachallenge.ui.model.mapToModelUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Jesus Villa on 11/12/24.
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    @SearchCitiesUseCaseInjection private val searchCitiesUseCase: SearchCitiesUseCase,
) : ViewModel()  {

    private val TAG = "MainViewModel"
    val intentChannel = Channel<CityIntent>(Channel.UNLIMITED)

    //var state by mutableStateOf(NewsScreenState())

    private val _state = MutableStateFlow(CityState())
    val state: StateFlow<CityState> = _state.asStateFlow()

    init {
        handleIntents()
    }

    private fun handleIntents() {
        viewModelScope.launch {
            intentChannel.consumeAsFlow().collect { intent ->
                Log.i(TAG, "when intent:$intent")
                when (intent) {
                    is CityIntent.LoadCities -> {
                        searchCities()
                    }
                    is CityIntent.FilterCities -> filterCities(intent.query)
                    is CityIntent.NavigateMap -> {

                    }
                }
            }
        }
    }

    private fun filterCities(text: String) {
        Log.i(TAG, "filterCities")
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            val current = _state.value.cities.orEmpty()

            val filterList = current.filter { it.name.contains(text, ignoreCase = true) }.map { it.copy(isSelected = false) }

            val finalList = filterList.ifEmpty { current }
            _state.update { currentState ->
                currentState.copy(isLoading = false, cities = finalList)
            }
        }
    }

    private fun searchCities() {
        Log.i(TAG, "searchCities")
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            searchCitiesUseCase.invoke().collect {
                _state.update { currentState ->
                    when (it) {
                        is Resource.Loading -> currentState.copy(isLoading = true)
                        is Resource.Success -> currentState.copy(isLoading = false, cities = it.data.mapToModelUi())
                        is Resource.Error -> currentState.copy(isLoading = false, errorMessage = it.message)
                        else -> {
                            currentState.copy(isLoading = false)
                        }
                    }
                }
            }
        }
    }

}