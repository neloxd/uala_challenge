package com.jesusvilla.ualachallenge

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jesusvilla.ualachallenge.ui.model.CityModel
import com.jesusvilla.ualachallenge.ui.screen.FilterListScreen
import com.jesusvilla.ualachallenge.ui.screen.MapScreen
import com.jesusvilla.ualachallenge.ui.screen.cities
import com.jesusvilla.ualachallenge.ui.theme.UalaChallengeTheme
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

                FilterListScreen(
                    onQueryChange = { text ->
                        run {
                            itemsList.clear()
                            if (text.isEmpty()) {
                                itemsList.addAll(cities)
                            } else {
                                itemsList.addAll(
                                    cities.filter { city -> city.name.contains(other = text, ignoreCase = true) }
                                )
                            }
                            Log.i(TAG, "itemsList -> ${itemsList.size}")
                        }
                    },
                    list = itemsList,
                    )
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
        FilterListScreen(list = cities, onQueryChange = {})
    }
}