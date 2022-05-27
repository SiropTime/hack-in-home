package com.maltsev.stankinhack.ui.items

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Mic
import androidx.compose.material.icons.outlined.Send
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.maltsev.stankinhack.screens.canTalking
import com.maltsev.stankinhack.screens.messageFieldText
import com.maltsev.stankinhack.screens.messagesList
import com.maltsev.stankinhack.utils.Message

@Composable
fun SendMessageUI() {
    val tempList = remember { mutableStateListOf<Message>() }
    tempList.swapList(messagesList)
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
                .widthIn(max = 240.dp)
                .clip(CircleShape)
                .border(
                    color = MaterialTheme.colors.primary,
                    width = 2.dp,
                    shape = CircleShape
                ),
            value = messageFieldText.value,
            placeholder = { Text(text = "Введите сообщение") } ,
            singleLine = true,
            onValueChange = {
                canTalking.value = it == ""
                messageFieldText.value = it
            })

        if (canTalking.value) {
            // Voice button
            OutlinedButton(
                onClick = {

                },
                modifier = Modifier
                    .padding(2.dp)
                    .size(50.dp)

                    .border(
                        color = MaterialTheme.colors.primary,
                        width = 2.dp,
                        shape = CircleShape
                    ),
                contentPadding = PaddingValues(0.dp),
                shape = CircleShape,
            ) {
                Icon(Icons.Outlined.Mic, "Talk")
            }
        } else {
            // Send Button
            OutlinedButton(
                modifier = Modifier
                    .padding(2.dp)
                    .size(50.dp)
                    .border(
                        color = MaterialTheme.colors.primary,
                        width = 2.dp,
                        shape = CircleShape
                    ),
                shape = CircleShape,
                contentPadding = PaddingValues(0.dp),
                onClick = {
                    canTalking.value = true
                    messagesList.add(Message("man", messageFieldText.value))
                    tempList.swapList(messagesList)
                    messageFieldText.value = ""
                }
            ) {
                Icon(Icons.Outlined.Send, "Send")
            }
        }
    }
}