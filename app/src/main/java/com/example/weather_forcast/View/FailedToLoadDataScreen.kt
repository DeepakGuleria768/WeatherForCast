package com.example.weather_forcast.View

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weather_forcast.Model.API.WeatherModel
import com.example.weather_forcast.R

@Composable
fun FailedToLoadData(message: String) {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.warningimage),
                contentDescription = "warning Image",
                modifier = Modifier.size(200.dp)
            )
            Spacer(Modifier.height(20.dp))
            Text( text = "Oops! Something went wrong...",
                fontSize = 24.sp,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold)
        }


    }
}

@Preview
@Composable
fun FailedToLoadDataPreview() {
    FailedToLoadData("Error")
}
