package com.jesusvilla.ualachallenge.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.jesusvilla.ualachallenge.R
import com.jesusvilla.ualachallenge.ui.component.CityListComponent
import com.jesusvilla.ualachallenge.ui.model.CityIntent
import com.jesusvilla.ualachallenge.ui.model.CityModel
import com.jesusvilla.ualachallenge.ui.model.CoordinatesModel
import com.jesusvilla.ualachallenge.ui.theme.UalaChallengeTheme
import com.jesusvilla.ualachallenge.ui.viewModel.MainViewModel
import kotlinx.coroutines.launch

/**
 * Created by Jesus Villa on 7/12/24.
 */

const val TAG = "FilterListScreen"


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterListScreen(
    viewModel: MainViewModel = hiltViewModel(),
    onSelected: (CityModel) -> Unit
) {

    var searchQuery by remember { mutableStateOf("") }
    val state by viewModel.state.collectAsState()
    val scope = rememberCoroutineScope()


    // Initialize session when screen loads
    LaunchedEffect(Unit) {
        scope.launch {
            viewModel.intentChannel.send(CityIntent.LoadCities)
        }
    }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column {
            SearchBar(
                query = searchQuery,//text showed on SearchBar
                onQueryChange = {
                    searchQuery = it
                    scope.launch {
                        viewModel.intentChannel.send(CityIntent.FilterCities(query = searchQuery))
                    }
                    //onQueryChange(searchQuery)
                                }, //update the value of searchText
                onSearch = {
                    Log.i(TAG, "onSearch -> $it")
                }, //the callback to be invoked when the input service triggers the ImeAction.Search action
                active = true, //whether the user is searching or not
                onActiveChange = {  }, //the callback to be invoked when this search bar's active state is changed
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Rounded.Search,
                        contentDescription = ""
                    )
                },
                placeholder = { Text(text = LocalContext.current.getString(R.string.place_holder_text)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(innerPadding)
            ) {
                Box (modifier = Modifier
                    .fillMaxSize()
                    //.padding(innerPadding)
                ) {
                    when {
                        state.isLoading -> {
                            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                                CircularProgressIndicator()
                            }
                        }
                        state.errorMessage != null -> {
                            Text("Error: ${state.errorMessage}", color = MaterialTheme.colorScheme.error)
                        }
                        else -> {
                            CityListComponent(
                                itemsList = ArrayList(state.cities.orEmpty()),
                                onSelected = { index, item ->
                                    Log.i(TAG, "onCitySelected:$item - index:$index")
                                    onSelected(item)
                                })
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FilterListPreview() {
    UalaChallengeTheme {

    }
}

val cities = ArrayList(listOf(
    CityModel(
        country = "UA",
        name = "Hurzuf",
        id = 707860,
        coordinates = CoordinatesModel(
            longitude = 34.283333,
            latitude = 44.549999,
        )
    ),
    CityModel(
        country = "RU",
        name = "Novinki",
        id = 519188,
        coordinates = CoordinatesModel(
            longitude = 34.283333,
            latitude = 44.549999,
        ),
    ),
    CityModel(
        country = "NP",
        name = "Gorkhā",
        id = 1283378,
        coordinates = CoordinatesModel(
            longitude = 34.283333,
            latitude = 44.549999,
        )
    ),
    CityModel(
        country = "IN",
        name = "State of Haryāna",
        id = 1270260,
        coordinates = CoordinatesModel(
            longitude = 34.283333,
            latitude = 44.549999,
        )
    ),
    CityModel(
        country = "UA",
        name = "Hurzuf",
        id = 707860,
        coordinates = CoordinatesModel(
            longitude = 34.283333,
            latitude = 44.549999,
        )
    ),
    CityModel(
        country = "UA",
        name = "Hurzuf",
        id = 707860,
        coordinates = CoordinatesModel(
            longitude = 34.283333,
            latitude = 44.549999,
        )
    ),
))
