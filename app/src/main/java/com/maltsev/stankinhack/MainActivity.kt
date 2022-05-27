package com.maltsev.stankinhack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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
            navController = rememberNavController()
            StankinHackTheme {
                SetupNavGraph(navController = navController)
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    StankinHackTheme {
        Greeting("Android")
    }
}