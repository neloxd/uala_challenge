package com.jesusvilla.ualachallenge.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState
import com.jesusvilla.ualachallenge.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen() {
    val atasehir = LatLng(40.9971, 29.1007)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(atasehir, 15f)
    }

    val currentOrientation = LocalConfiguration.current.orientation

    when (currentOrientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                Column {
                    GoogleMap(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                        ,
                        cameraPositionState = cameraPositionState
                    )
                }
            }
        }
        else -> {
            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                Column {
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
                            IconButton(onClick = { }) {
                                Icon(Icons.Filled.ArrowBack, "backIcon")
                            }
                        }
                    )

                    Box (modifier = Modifier
                        .fillMaxSize()
                    ) {
                        GoogleMap(
                            modifier = Modifier
                                .fillMaxSize(),
                            cameraPositionState = cameraPositionState
                        )
                        Text("HOLAAAAAAA", color = Color.Black)

                    }
                    /*GoogleMap(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        cameraPositionState = cameraPositionState
                    )*/
                }
            }
        }
    }
}