package com.jesusvilla.ualachallenge.ui.component

import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jesusvilla.ualachallenge.ui.model.CityModel
import com.jesusvilla.ualachallenge.ui.model.CoordinatesModel
import com.jesusvilla.ualachallenge.ui.screen.cities
import com.jesusvilla.ualachallenge.ui.theme.UalaChallengeTheme

@Composable
fun CityListComponent(
    itemsList: ArrayList<CityModel>,
    onSelected: (Int, CityModel) -> Unit,
) {
    val TAG = "CityListComponent"
    val items by remember { mutableStateOf(itemsList) }
    Log.i(TAG, "CityListComponent -> items:${items.size}")

    //items.addAll(itemsList)

    var lastIndex = -1

    var selectedIndex by remember { mutableStateOf(-1) }

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        itemsIndexed(itemsList) { index, value ->
            Column (modifier = Modifier
                .padding(top = 10.dp)
                /*.clickable {
                    if (lastIndex != -1) {
                        items[lastIndex] = items[lastIndex].copy(isSelected = false)
                    }
                    lastIndex = index

                    val isSelected = value.isSelected
                    if(isSelected) {
                        items[index] = value.copy(isSelected = false)
                    } else {
                        items[index] = value.copy(isSelected = true)
                    }
                }*/
            ) {
                CityCard(
                    name = "${value.name} - ${value.id}",
                    cityModel = value,
                    isSelected = index == selectedIndex,
                    onColorChange = {
                        selectedIndex = index
                        onSelected(index, value)
                })
            }
        }
    }

    /*LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        itemsIndexed(newList) { city ->
            val color by remember { mutableStateOf(Color.Transparent) }

            val name = "${city.name} - ${city.id}"
            var selectedCard by remember { mutableStateOf(false) }
            var cardColor = if (selectedCard) Color.Yellow else Color.Transparent

            CityCard(
                name = name,
                backgroundColor = cardColor,
                onColorChange = {
                    Log.i(TAG, "index:$index - onColorChange:$selectedCard")
                    selectedCard = !selectedCard
                    //val newColor = if(city.color == Color.Transparent) Color.Yellow else Color.Transparent
                    //itemList[index] = city.copy(color = newColor)
                }
            )
        }
    }*/
}

@Composable
fun CityCard(
    name: String,
    cityModel: CityModel,
    //backgroundColor: Color,
    isSelected: Boolean,
    onColorChange: () -> Unit
) {
    var isPressed by remember { mutableStateOf(cityModel.isSelected) }
    val buttonColor by animateColorAsState(
        targetValue = when (isPressed) {
            true -> Color.Yellow
            false -> Color.Transparent
        },
        animationSpec = tween(), label = ""
    )

    Log.i("CityCard", "cityModel:$cityModel")
     // by mutableStateOf(
    val itemBackgroundColor = if (isSelected) Color.Yellow else Color.Transparent

    /*Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column ( modifier = Modifier.background(
            color = buttonColor//backgroundColor,
        ).padding(12.dp)
            .clickable {
                isPressed = !isPressed
            }
            .fillMaxWidth()){
            Text(
                text = name,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }*/

    Row(modifier = Modifier
        .background(color = itemBackgroundColor)
        .clickable {
            onColorChange()
        }) {
        //other detail codes ...
        Text(
            text = name,
            modifier = Modifier
                .fillMaxWidth()
                //.background(shape = RoundedCornerShape(24.dp), color = buttonColor)
                .padding(horizontal = 16.dp, vertical = 8.dp)

                /*.clickable {
                    isPressed = !isPressed
                }*/
        )
    }


}

@Preview(showBackground = true)
@Composable
fun CityListComponentPreview() {
    UalaChallengeTheme {
        CityListComponent(
            itemsList = ArrayList(listOf(
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
            )),
            onSelected = { _, _ -> {

            } },
        )
    }
}