package com.maltsev.stankinhack.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Preview(showBackground = true, showSystemUi = true, device = Devices.PIXEL_4)
@Composable
fun MainScreenPreview() {
    MainScreen(navController = rememberNavController())
}

@Composable
fun MainScreen(navController: NavController) {
    Surface {

        Column(
           modifier = Modifier
               .fillMaxSize()
               .padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween

        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                    ) {

            }
        }
    }
}