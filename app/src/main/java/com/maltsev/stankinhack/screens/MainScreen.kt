package com.maltsev.stankinhack.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Mic
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material.icons.outlined.Send
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
    val messageFieldText = remember {
        mutableStateOf("")
    }

    val canTalking = remember {
        mutableStateOf(true)
    }

    Surface {

        Column(
           modifier = Modifier
               .fillMaxSize()
               .padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween

        ) {

            // Row with message field, voice button and send button
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
                    ) {
                OutlinedTextField(
                    modifier = Modifier
                        .padding(2.dp)
                        .height(50.dp)
                        .clip(CircleShape)
                        .border(
                            color = MaterialTheme.colors.primary,
                            width = 2.dp,
                            shape = CircleShape
                        ),
                    value = messageFieldText.value,
                    onValueChange = {
                        canTalking.value = false
                        messageFieldText.value = it
                    })

                if (canTalking.value) {
                    // Voice button
                    OutlinedButton(
                        onClick = {

                        },
                        modifier = Modifier
                            .padding(2.dp)
                            .height(50.dp)
                            .border(
                                color = MaterialTheme.colors.primary,
                                width = 2.dp,
                                shape = CircleShape
                            ),
                        shape = CircleShape,
                        ) {
                        Icon(Icons.Outlined.Mic, "Talk")
                    }
                } else {
                    // Send Button
                    OutlinedButton(
                        modifier = Modifier
                            .padding(2.dp)
                            .height(50.dp)
                            .border(
                                color = MaterialTheme.colors.primary,
                                width = 2.dp,
                                shape = CircleShape
                            ),
                        shape = CircleShape,
                        onClick = {
                            canTalking.value = true
                            messageFieldText.value = ""
                        }
                    ) {
                        Icon(Icons.Outlined.Send, "Send")
                    }
                }
            }
        }
    }
}