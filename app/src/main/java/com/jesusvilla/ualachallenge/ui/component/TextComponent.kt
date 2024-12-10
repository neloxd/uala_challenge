package com.jesusvilla.ualachallenge.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jesusvilla.ualachallenge.ui.model.CityModel
import com.jesusvilla.ualachallenge.ui.model.CoordinatesModel
import com.jesusvilla.ualachallenge.ui.theme.UalaChallengeTheme

/**
 * Created by Jesus Villa on 10/12/24.
 */

@Composable
fun TextComponent(
    cityModel: CityModel,
    onCitySelected: (CityModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    Card (modifier = Modifier.clickable { onCitySelected(cityModel) }){
        Column ( modifier= Modifier.background(
            color = if(cityModel.isSelected) Color.Yellow else Color.Transparent
        ).padding(12.dp)){
            Text(
                text = "${cityModel.name} - ${cityModel.id}",
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }

    }
}

//{"country":"UA","name":"Hurzuf","_id":707860,"coord":{"lon":34.283333,"lat":44.549999}}
@Preview(showBackground = true)
@Composable
fun TextComponentPreview() {
    UalaChallengeTheme {
        TextComponent(
            cityModel = CityModel(
                country = "UA",
                name = "Hurzuf",
                id = 707860,
                coordinates = CoordinatesModel(
                    longitude = 34.283333,
                    latitude = 44.549999,
                )
            ),
            onCitySelected = { },
        )
    }
}