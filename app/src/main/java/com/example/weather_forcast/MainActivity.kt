package com.example.weather_forcast

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weather_forcast.View.HomeScreen
import com.example.weather_forcast.View.SplashScreen
import com.example.weather_forcast.ui.theme.Weather_forcastTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Weather_forcastTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    appNavigation()
                }

            }
        }
    }
}

@Composable
fun appNavigation() {
    val navController = rememberNavController()

    NavHost(startDestination = "Splash", navController = navController) {
        composable("Splash") {
            SplashScreen(
                onAnimationFinished = {
                    navController.navigate("Home") {
                        popUpTo("Splash") { inclusive = true }
                    }
                }
            )
        }
        composable("Home"){
            HomeScreen()
        }
    }
}