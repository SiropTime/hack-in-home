package com.maltsev.stankinhack.ui.items

import android.Manifest
import android.content.Context
import android.content.Intent

import android.content.pm.PackageManager
import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder
import android.os.Build
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Mic
import androidx.compose.material.icons.outlined.Send
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.startActivityForResult
import com.maltsev.stankinhack.screens.canTalking
import com.maltsev.stankinhack.screens.messageFieldText
import com.maltsev.stankinhack.screens.messagesList
import com.maltsev.stankinhack.utils.Message
import com.maltsev.stankinhack.utils.makeSendingAudio
import com.maltsev.stankinhack.utils.makeSendingMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException

var audioRecord: AudioRecord? = null
val AUDIO_SOURCE = MediaRecorder.AudioSource.MIC
val SAMPLE_RATE = 44100
val CHANNEL_CONFIG = AudioFormat.CHANNEL_IN_MONO
val AUDIO_FORMAT = AudioFormat.ENCODING_PCM_8BIT
val BUFFER_SIZE_RECORDING = AudioRecord.getMinBufferSize(SAMPLE_RATE, CHANNEL_CONFIG, AUDIO_FORMAT)

val isListening = mutableStateOf(false)

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SendMessageUI() {
    val tempList = remember { mutableStateListOf<Message>() }

    val keyboardController = LocalSoftwareKeyboardController.current
    val isPressed = remember { mutableStateOf(false) }

    tempList.swapList(messagesList)
    val context = LocalContext.current
    val path = context.filesDir
    val audioDirectory =  File(path, "audio")
    audioDirectory.mkdirs()
    val file = File(audioDirectory, "voice.wav")
        Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            modifier = Modifier
                .padding(vertical = 2.dp, horizontal = 5.dp)
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
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                    makeSendingMessage(messageFieldText.value, context)
                    canTalking.value = true
                    messagesList.add(Message("man", messageFieldText.value))
                    tempList.swapList(messagesList)
                    messageFieldText.value = ""
                }
            ),
            onValueChange = {
                canTalking.value = it == ""
                messageFieldText.value = it
            })

        if (canTalking.value) {
            // Voice button
            OutlinedButton(
                onClick = {
                    if (!isPressed.value) {
                        isListening.value = true
                        isPressed.value = true
                        CoroutineScope(Dispatchers.IO).launch {
                            startRecording(context = context)
                            file.createNewFile()
                            writeAudioData(file)

                        }
                    } else {

                        isListening.value = false
                        isPressed.value = false
                        CoroutineScope(Dispatchers.IO).launch {
                            makeSendingAudio(context)
                        }

                    }
                },
                modifier = Modifier
                    .padding(vertical = 2.dp, horizontal = 4.dp)
                    .size(50.dp)
                    .border(
                        color = MaterialTheme.colors.primary,
                        width = 2.dp,
                        shape = CircleShape
                    )
                    .clip(CircleShape),
                colors = ButtonDefaults.buttonColors(
                    contentColor = if (isPressed.value) MaterialTheme.colors.onPrimary else MaterialTheme.colors.onBackground,
                    backgroundColor = if (isPressed.value) MaterialTheme.colors.primary else Color.White
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
                    .padding(vertical = 2.dp, horizontal = 4.dp)
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

fun startRecording(context: Context) {
    if (ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.RECORD_AUDIO
        ) != PackageManager.PERMISSION_GRANTED
    ) {

        return
    }
    audioRecord = AudioRecord(AUDIO_SOURCE, SAMPLE_RATE, CHANNEL_CONFIG, AUDIO_FORMAT, BUFFER_SIZE_RECORDING)
    if (audioRecord!!.getState() != AudioRecord.STATE_INITIALIZED) {
        Log.e("AUDIO", "error initializing")
    }
    audioRecord!!.startRecording()

}

fun writeAudioData(fileName: File) {
    var data = ByteArray(BUFFER_SIZE_RECORDING/2)
    var outputStream : FileOutputStream? = null
    try {
        outputStream = FileOutputStream(fileName)
    } catch (e: FileNotFoundException) {
        Log.e("AUDIO", "COULDN'T CREATE OUTPUT STREAM")
        e.printStackTrace()
    }
    while (isListening.value) {
        val read = audioRecord!!.read(data, 0, data.size)
        try {
            outputStream!!.write(data, 0, read)
        } catch (e: IOException) {
            Log.d("AUDIO", "ERROR WHILE WRITING IN FILE")
            e.printStackTrace()
        }

    }
    try {
        outputStream!!.flush()
        outputStream.close()
    } catch (e: IOException) {
        Log.d("AUDIO", "exception while closing output stream " + e.toString())
        e.printStackTrace()
    }
    Log.d("AUDIO", "SUCCESSFUL")
    audioRecord!!.stop()
    audioRecord!!.release()
    audioRecord = null

}