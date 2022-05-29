package com.maltsev.stankinhack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.gson.GsonBuilder
import com.maltsev.stankinhack.ui.theme.StankinHackTheme
import com.maltsev.stankinhack.utils.SetupNavGraph
import com.maltsev.stankinhack.utils.SpeechService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BOT_SENDER = "bot"

val builder = GsonBuilder()
val gson = builder.setLenient().create()
//var recorder: MediaRecorder? = null


val retrofit : Retrofit = Retrofit.Builder()
    .baseUrl("http://192.168.8.133:8080")
    .addConverterFactory(GsonConverterFactory.create(gson))
    .build()

 val speechService: SpeechService = retrofit.create(SpeechService::class.java)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var navController: NavHostController

        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current


            StankinHackTheme {
                navController = rememberNavController()
                SetupNavGraph(navController = navController)
            }
        }
    }
}

