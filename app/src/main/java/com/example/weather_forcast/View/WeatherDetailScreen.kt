package com.example.weather_forcast.View

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsEndWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.toFontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.weather_forcast.Model.API.Condition
import com.example.weather_forcast.Model.API.Current
import com.example.weather_forcast.Model.API.Location
import com.example.weather_forcast.Model.API.WeatherModel
import com.example.weather_forcast.R
import com.example.weather_forcast.ui.theme.Weather_forcastTheme
import com.example.weather_forcast.ui.theme.myFontFamily

@Composable
fun WeatherDetailsScreen(data: WeatherModel) {


    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Row to represent the location icon and the location name
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.Bottom
        ) {

            Icon(
                Icons.Default.LocationOn,
                contentDescription = "location Icon",
                modifier = Modifier.size(40.dp),
                tint = MaterialTheme.colorScheme.onBackground
            )
            Spacer(Modifier.width(10.dp))
            Text(
                text = data.location.name,
                fontSize = 30.sp,
                fontFamily = myFontFamily,
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colorScheme.primary,
            )
            Spacer(Modifier.width(8.dp))
            Text(
                text = data.location.country,
                fontSize = 15.sp,
                fontFamily = Font(R.font.ancizarfontone).toFontFamily(),
                color = MaterialTheme.colorScheme.primary
            )
        }
        Spacer(Modifier.height(20.dp))
        // show temp in C
        Text(
            text = "${data.current.temp_c} ° c",
            fontSize = 45.sp,
            fontFamily = myFontFamily,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        AsyncImage(
            model = "https:${data.current.condition.icon}",
            contentDescription = "image",
            modifier = Modifier.size(160.dp),
        )
        Spacer(Modifier.height(40.dp))
        Text(
            text = data.current.condition.text,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Medium
        )
        Spacer(Modifier.height(30.dp))

        // create elevated card  here

        Column(
            modifier = Modifier.wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.SpaceAround
            )
            {

                CardComponent(
                    value = "${data.current.humidity}%",
                    title = "Humidity", modifier = Modifier.weight(1f)
                )
                Spacer(Modifier.width(8.dp))
                CardComponent(
                    value = "${data.current.wind_kph}kph",
                    title = "Wind Speed", modifier = Modifier.weight(1f)
                )

            }
            Spacer(Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.SpaceAround
            )
            {

                CardComponent(
                    value = data.current.uv,
                    title = "UV", modifier = Modifier.weight(1f)
                )
                Spacer(Modifier.width(8.dp))
                CardComponent(
                    value = "${data.current.pressure_mb}mb",
                    title = "pressure", modifier = Modifier.weight(1f)

                )

            }
            Spacer(Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.SpaceAround
            )
            {
                CardComponent(
                    value = "${data.current.windchill_c} °c",
                    title = "Wind Chill",
                    modifier = Modifier.weight(1f)
                )
                Spacer(Modifier.width(8.dp))
                CardComponent(
                    value = "${data.current.feelslike_c} °c",
                    title = "Feels Like",
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}


