package com.maltsev.stankinhack.utils

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import com.maltsev.stankinhack.BOT_SENDER
import com.maltsev.stankinhack.screens.messagesList
import com.maltsev.stankinhack.speechService
import com.maltsev.stankinhack.ui.items.swapList
import kotlinx.coroutines.*
import okhttp3.*
import okhttp3.MediaType.Companion.parse
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import java.io.File

val SPEECH_URL = ""

interface SpeechService {
    @POST("/message")
    suspend fun sendMessage(@Body requestBody: RequestBody): Response<Message>

    @Multipart
    @POST("/listen")
    suspend fun sendAudio(@Part requestBody: RequestBody, @Part file: MultipartBody.Part): Response<Message>
}

fun makeSendingAudio(context: Context) {
    val client = OkHttpClient()
    val path = context.filesDir
    val audioDirectory =  File(path, "audio")
    audioDirectory.mkdirs()
    val file = File(audioDirectory, "voice.wav")
    val requestBody = MultipartBody.Builder().setType(MultipartBody.FORM)
        .addFormDataPart("file", "audio", RequestBody.create("audio/x-wav".toMediaTypeOrNull(), file))
        .build()
    val request = Request.Builder()
        .url("http://192.168.8.133:8080/listen")
        .post(requestBody).build()
    var response = client.newCall(request).execute()
    Log.d("SEND_AUDIO", response.message)

}

fun makeSendingMessage(message: String, context: Context) {
    val jsonObject = JSONObject()
    jsonObject.put("text", message)

    val requestBody = jsonObject.toString().toRequestBody("application/json".toMediaTypeOrNull())
    Log.d("REQUEST", jsonObject.toString())
    CoroutineScope(Dispatchers.IO).launch {
        val response = speechService.sendMessage(requestBody)

        withContext(Dispatchers.IO) {
            if (response.isSuccessful) {
                if (response.code() == 200) {
                    val messageBody = response.body()
                    Log.d("MESSAGE_BODY", messageBody.toString())
                    val messageText = messageBody?.text?: "N/A"
                    Log.d("MESSAGE_TEXT", messageText)
                    val messageSender = BOT_SENDER
                    val messageResponse = Message(messageSender, messageText)
                    val job = GlobalScope.launch(Dispatchers.IO) {
                        val tempList = mutableStateListOf<Message>()
                        tempList.swapList(messagesList)
                        messagesList.add(messageResponse)
                        tempList.swapList(messagesList)
                        Log.d("MESSAGES", messagesList.toString())
                    }
                    job.join()
                } else if (response.code() == 202) {
                    val messageText = "Извини, я не понял, что ты имеешь ввиду"
                    val messageSender = BOT_SENDER
                    val messageResponse = Message(messageSender, messageText)
                    val job = GlobalScope.launch(Dispatchers.IO) {
                        val tempList = mutableStateListOf<Message>()
                        tempList.swapList(messagesList)
                        messagesList.add(messageResponse)
                        tempList.swapList(messagesList)
                    }
                    job.join()
                }

            } else {
                Log.e("HTTP", "Failed sending message")
                var errorBody = ""
                response.errorBody()?.let{errorBody = it.string()}
                Log.e("ERROR", errorBody)
            }
        }
    }
}