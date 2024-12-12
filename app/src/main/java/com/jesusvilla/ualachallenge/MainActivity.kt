package com.jesusvilla.ualachallenge

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.jesusvilla.ualachallenge.ui.model.CityModel
import com.jesusvilla.ualachallenge.ui.navigation.NavGraphSetUp
import com.jesusvilla.ualachallenge.ui.screen.FilterListScreen
import com.jesusvilla.ualachallenge.ui.screen.MapScreen
import com.jesusvilla.ualachallenge.ui.screen.cities
import com.jesusvilla.ualachallenge.ui.theme.UalaChallengeTheme
import com.jesusvilla.ualachallenge.ui.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    companion object {
        const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UalaChallengeTheme {
                //MapScreen()
                //val itemsList = remember { mutableStateListOf<CityModel>() }
                val itemsList = ArrayList(cities)
                /*val itemsList = remember { mutableStateListOf<CityModel>().apply {
                    clear()
                       addAll(cities)
                } }*/

                //itemsList.addAll(cities)

                val currentOrientation = LocalConfiguration.current.orientation
                when(currentOrientation) {
                    Configuration.ORIENTATION_LANDSCAPE -> {
                        val viewModel: MainViewModel = hiltViewModel()
                        var city by remember { mutableStateOf<CityModel?>(null) }

                        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                            Row(modifier = Modifier
                                .fillMaxSize()
                                .padding(innerPadding)) {
                                Box(modifier = Modifier
                                    .fillMaxHeight()
                                    .weight(0.5f)) {
                                    FilterListScreen(
                                        viewModel = viewModel,
                                        onSelected = {
                                            city = it
                                        }
                                    )
                                }
                                Box(modifier = Modifier
                                    .fillMaxHeight()
                                    .weight(0.5f)) {
                                    MapScreen(
                                        cityModel = city
                                    )
                                }
                            }
                        }
                    }
                    else -> {
                        NavGraphSetUp()
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun MainFilterListScreenPreview() {
    UalaChallengeTheme {
        //FilterListScreen(list = cities, onQueryChange = {})
    }
}