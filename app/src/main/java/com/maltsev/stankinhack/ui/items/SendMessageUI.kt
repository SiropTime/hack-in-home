package com.maltsev.stankinhack.ui.items

import android.Manifest
import android.content.pm.PackageManager
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.maltsev.stankinhack.screens.canTalking
import com.maltsev.stankinhack.screens.messageFieldText
import com.maltsev.stankinhack.screens.messagesList
import com.maltsev.stankinhack.utils.Message
import com.maltsev.stankinhack.utils.makeSendingMessage


@Composable
fun SendMessageUI() {
    val tempList = remember { mutableStateListOf<Message>() }
    val isListening = remember { mutableStateOf(false) }
    tempList.swapList(messagesList)
    val context = LocalContext.current
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

//                    if (context.checkSelfPermission(Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
//                        if (isListening.value) {
////                            recorder?.stop()
////                            recorder?.release()
//                            isListening.value = false
//                        } else {
////                            recorder?.prepare()
////                            recorder?.start()
//                            isListening.value = true
//                        }
//                    }
                },
                modifier = Modifier
                    .padding(2.dp)
                    .size(50.dp)

                    .border(
                        color = MaterialTheme.colors.primary,
                        width = 2.dp,
                        shape = CircleShape
                    ),
                colors = ButtonDefaults.buttonColors(),
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
                    makeSendingMessage(messageFieldText.value, context)
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