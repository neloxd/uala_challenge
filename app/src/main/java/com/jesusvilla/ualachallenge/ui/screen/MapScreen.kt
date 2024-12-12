package com.jesusvilla.ualachallenge.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.jesusvilla.ualachallenge.R
import com.jesusvilla.ualachallenge.ui.model.CityModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen(cityModel: CityModel? = null, onBackPressed: (() -> Unit)? = null) {

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column {
            onBackPressed?.let {
                TopAppBar(
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    title = {
                        Text(text = LocalContext.current.getString(R.string.title_back), color = Color.Black,
                            fontSize = 18.sp)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    //.background(Color.White)
                    //.height(56.dp),
                    navigationIcon = {
                        IconButton(onClick = {
                            onBackPressed()
                        }) {
                            Icon(Icons.Filled.ArrowBack, "backIcon")
                        }
                    }
                )
            }

            Box (modifier = Modifier
                .fillMaxSize()
            ) {
                val initialZoom = 6f
                val finalZoom = 15f

                val currentPosition = cityModel?.let {
                    LatLng(cityModel.coordinates.latitude, cityModel.coordinates.longitude)
                }?: LatLng(40.9971, 29.1007)

                val cameraPositionState = rememberCameraPositionState {
                    position = CameraPosition.fromLatLngZoom(currentPosition, initialZoom)
                }

                LaunchedEffect(currentPosition) {
                    cameraPositionState.animate(
                        update = CameraUpdateFactory.newCameraPosition(
                            CameraPosition(currentPosition, finalZoom, 0f, 0f)
                        ),
                        durationMs = 1000
                    )
                }

                GoogleMap(
                    modifier = Modifier
                        .fillMaxSize(),
                    cameraPositionState = cameraPositionState
                ) {
                    cityModel?.let {
                        Marker(
                            state = MarkerState(position = currentPosition),
                            title = "${cityModel.name} - ${cityModel.country}"
                        )
                    }
                }
            }
            /*GoogleMap(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                cameraPositionState = cameraPositionState
            )*/
        }
    }
//    when (currentOrientation) {
//        Configuration.ORIENTATION_LANDSCAPE -> {
//            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                Column {
//                    GoogleMap(
//                        modifier = Modifier
//                            .fillMaxSize()
//                            .padding(innerPadding)
//                        ,
//                        cameraPositionState = cameraPositionState
//                    )
//                }
//            }
//        }
//        else -> {
//            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                Column {
//                    TopAppBar(
//                        colors = TopAppBarDefaults.smallTopAppBarColors(
//                            containerColor = MaterialTheme.colorScheme.primaryContainer,
//                            titleContentColor = MaterialTheme.colorScheme.primary,
//                        ),
//                        title = {
//                            Text(text = LocalContext.current.getString(R.string.title_back), color = Color.Black,
//                                fontSize = 18.sp)
//                        },
//                        modifier = Modifier.fillMaxWidth(),
//                            //.background(Color.White)
//                            //.height(56.dp),
//                        navigationIcon = {
//                            IconButton(onClick = {
//                                onBackPressed()
//                            }) {
//                                Icon(Icons.Filled.ArrowBack, "backIcon")
//                            }
//                        }
//                    )
//
//                    Box (modifier = Modifier
//                        .fillMaxSize()
//                    ) {
//                        GoogleMap(
//                            modifier = Modifier
//                                .fillMaxSize(),
//                            cameraPositionState = cameraPositionState
//                        )
//                    }
//                    /*GoogleMap(
//                        modifier = Modifier
//                            .fillMaxSize()
//                            .padding(innerPadding),
//                        cameraPositionState = cameraPositionState
//                    )*/
//                }
//            }
//        }
//    }
}