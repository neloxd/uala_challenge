package com.jesusvilla.ualachallenge.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jesusvilla.ualachallenge.ui.model.CityModel
import com.jesusvilla.ualachallenge.ui.model.CoordinatesModel
import com.jesusvilla.ualachallenge.ui.theme.UalaChallengeTheme

@Composable
fun CityListComponent(
    cities: List<CityModel>,
    onSelected: (CityModel) -> Unit,
) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        items(cities) { city ->
            TextComponent(
                cityModel = city,
                onCitySelected = { onSelected(city) },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CityListComponentPreview() {
    UalaChallengeTheme {
        CityListComponent(
            cities = listOf(
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
                    ),
                    isSelected = true
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
            ),
            onSelected = { },
        )
    }
}