package com.example.weather_forcast.View

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CardComponent(
    value: String,
    title: String,
    modifier: Modifier
) {
    ElevatedCard(
        modifier = Modifier
            .width(180.dp)
            .height(120.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.secondary),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = value,
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold
            , color = MaterialTheme.colorScheme.onPrimary
                )
            Text(text = title,
                fontSize = 15.sp,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.padding(5.dp))
        }
    }
}

@Preview
@Composable
fun CardComponentPreview() {
    CardComponent("Humidity", "32.6",modifier = Modifier)
}