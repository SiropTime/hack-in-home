package com.maltsev.stankinhack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.gson.GsonBuilder
import com.maltsev.stankinhack.ui.theme.StankinHackTheme
import com.maltsev.stankinhack.utils.SetupNavGraph
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val builder = GsonBuilder()
val gson = builder.setLenient().create()

val retrofit : Retrofit = Retrofit.Builder()
    .baseUrl("")
    .addConverterFactory(GsonConverterFactory.create(gson))
    .build()

// Don't forget ut here class of service
// val service = retrofit.create(ApiService::class.java)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        lateinit var navController: NavHostController

        super.onCreate(savedInstanceState)
        setContent {
            StankinHackTheme {
                navController = rememberNavController()
                SetupNavGraph(navController = navController)
            }
        }
    }
}
