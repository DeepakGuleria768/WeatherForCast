package com.example.weather_forcast.View

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.weather_forcast.R

@Composable
fun SplashScreen(
   onAnimationFinished: () -> Unit
) {
    //LottieCompositionSpec.RawRes(R.raw.splash_animation): This loads your Lottie animation from the raw resource directory.
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.splashscreenjsonfile))
    //animateLottieCompositionAsState: This is a Composable that manages the playback of your Lottie animation.
    //iterations = 1: Ensures the animation plays only once.
    //speed = 1f: Sets the animation speed.
    //animationSpec = tween(durationMillis = 2000): (Optional) You can use this to control the duration of the animation, regardless of its original framerate
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = 1, // play animation once only
        speed = 1f, // normal speed
    )
// This LaunchedEffect observes the progress of the Lottie animation.
// When progress reaches 1f (meaning the animation has completed), it calls the onAnimationFinished callback.
    LaunchedEffect(progress) {
        if (progress == 1f) {
            onAnimationFinished()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        LottieAnimation(
            composition = composition,
            progress = { progress },
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Preview
@Composable
fun SplashScreenPreview() {
    SplashScreen(TODO())
}