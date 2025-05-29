package com.example.weather_forcast.View

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weather_forcast.Model.API.NetworkResponseClass
import com.example.weather_forcast.ViewModel.WeatherViewModel

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun HomeScreen(
    viewModel: WeatherViewModel, modifier: Modifier = Modifier
) {

    // convert regular live data into state
    val weatherResult = viewModel.weatherResult.observeAsState()
    // calculateWindowSizeClass() gives you the windowSizeClass object,
    val windowSizeClass: WindowSizeClass = calculateWindowSizeClass()
    // Hoist state for search bar
    // This state now lives in HomeScreen and is passed down,
    // so it persists even if the screen changes (e.g., on rotation).
    var searchQuery: String by rememberSaveable { mutableStateOf("") }
    var searchIsActive: Boolean by rememberSaveable { mutableStateOf(false) }

    val dummySuggestions = remember {
        listOf(
            "New York",
            "London",
            "Paris",
            "Tokyo",
            "Mumbai",
            "Delhi",
            "Bengaluru",
            "Chennai",
            "Kolkata",
            "Hyderabad",
            "Pune",
            "Jaipur",
            "Lucknow",
            "pathankot",
            "China",
            "Russia",
            "Japan"
        )
    }
    // Filter suggestions based on current query
    //This is like saying, "Hey, I want you to remember what I'm looking for (my 'searchQuery' - cars!) and where all my toys are (my 'dummySuggestions' box)."
    // The computer will only go searching again if you change what you're looking for or if you get a whole new set of toys.
    val filteredSuggestions = remember(searchQuery, dummySuggestions) {
        dummySuggestions.filter { it.contains(searchQuery, ignoreCase = true) }
    }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        when (windowSizeClass.widthSizeClass) {
            WindowWidthSizeClass.Compact -> {
                CompactScreen(
                    searchQuery = searchQuery,
                    onSearchQueryChange = { searchQuery = it },
                    searchIsActive = searchIsActive,
                    onSearchActiveChange = { searchIsActive = it },
                    onCitySelected = { city ->
                        searchQuery = city // Set selected city as current query
                        searchIsActive = false // Collapse search bar
                        // call viewModel to fetch the data
                        viewModel.getData(city = city)
                    },

                    Suggestions = filteredSuggestions,
                    viewModel = viewModel
                )
            }

            WindowWidthSizeClass.Medium -> {
                MediumSizedScreen(
                    searchQuery = searchQuery,
                    onSearchQueryChange = { searchQuery = it },
                    searchIsActive = searchIsActive,
                    onSearchActiveChange = { searchIsActive = it },
                    onCitySelected = { city ->
                        searchQuery = city
                        searchIsActive = false
                        // call viewModel
                        viewModel.getData(city = city)
                    },
                    suggestions = filteredSuggestions,
                    viewModel = viewModel
                )
            }

            else -> {
                LargeSizedScreens(
                    searchQuery = searchQuery,
                    onSearchQueryChange = { searchQuery = it },
                    searchIsActive = searchIsActive,
                    onSearchActiveChange = { searchIsActive = it },
                    onCitySelected = { city ->
                        searchQuery = city
                        searchIsActive = false
                        // call viewModel
                        viewModel.getData(city = city)
                    },
                    suggestions = filteredSuggestions,
                    viewModel = viewModel
                )
            }
        }




    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompactScreen(
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    searchIsActive: Boolean,
    onSearchActiveChange: (Boolean) -> Unit,
    onCitySelected: (String) -> Unit,
    Suggestions: List<String>,
    viewModel: WeatherViewModel
) {
    // convert regular live data into state
    val weatherResult = viewModel.weatherResult.observeAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // search bar component
        SearchBar(
            query = searchQuery,
            onQueryChange = onSearchQueryChange,
            modifier = Modifier.background(MaterialTheme.colorScheme.background),
            onSearch = { submittedQuery ->
                onSearchActiveChange(false)
                onCitySelected(submittedQuery)
            },
            active = searchIsActive,
            onActiveChange = onSearchActiveChange, // update the active state
            placeholder = { Text("Search City ....") },
            leadingIcon = {
                if (searchIsActive)
                // show back arrow when active
                {
                    IconButton(
                        onClick = { onSearchActiveChange(false) }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Arrow Back")
                    }
                } else {
                    // show search icon only when inactive
                    Icon(Icons.Default.Search, contentDescription = "Search")
                }
            },
            trailingIcon = {
                // Icon on the right side of the search bar
                if (searchQuery.isNotEmpty()) {
                    IconButton(onClick = { onSearchQueryChange("") }) {
                        Icon(Icons.Default.Clear, contentDescription = "clear")
                    }
                }
            }

        ) {
            // here we will show the dummy data so when he search bar is expanded then thsi data
            // shown
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(Suggestions) { suggestions ->
                    ListItem(headlineContent = { Text(suggestions) }, leadingContent = {
                        Icon(
                            Icons.Default.LocationOn, contentDescription = null
                        )
                    }, modifier = Modifier
                        .clickable {
                            onCitySelected(suggestions)
                        }
                        .background(
                            MaterialTheme.colorScheme.background
                        )
                        .fillMaxSize())
                }
            }
        }
        // --- Spacer to separate Search Bar from Weather Display ---
        Spacer(modifier = Modifier.height(10.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            when (val result = weatherResult.value) {
                is NetworkResponseClass.Error -> {
                    FailedToLoadData(message = result.message)
                }

                NetworkResponseClass.loading -> {
                    CircularProgressIndicator(
                    )
                }

                is NetworkResponseClass.Success -> {
                    WeatherDetailsScreen(data = result.data)
                }

                null -> {}

            }
        }
    }
}
// WE HAVE ONE ISSUE ?????????
//State Management (SearchBar query and active):
//
//Currently, query and active are declared inside each screen composable (CompactScreen, MediumSizedScreen, LargeSizedScreens).
//This means if you type something in the search bar on a CompactScreen and then rotate your phone (causing it to switch to LargeSizedScreens),
// the search bar will become empty and inactive because it's a new instance of the state.

//Solution: You need to hoist the query and active states (and their onQueryChange, onActiveChange callbacks)
// to the HomeScreen level, and then pass them down as parameters to CompactScreen, MediumSizedScreen, and LargeSizedScreens.
// This ensures the search bar state persists across screen size changes.
// (I demonstrated this in my previous example, and you can adopt that pattern.)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MediumSizedScreen(
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    searchIsActive: Boolean,
    onSearchActiveChange: (Boolean) -> Unit,
    onCitySelected: (String) -> Unit,
    suggestions: List<String>,
    viewModel: WeatherViewModel
) {
    // convert regular live data into state
    val weatherResult = viewModel.weatherResult.observeAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp), // More padding
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        SearchBar( // Still a full-screen overlay when active
            query = searchQuery,
            onQueryChange = onSearchQueryChange,
            onSearch = { submittedQuery ->
                onSearchActiveChange(false)
                onCitySelected(submittedQuery)
            },
            active = searchIsActive,
            onActiveChange = onSearchActiveChange,
            placeholder = { Text("Search City ....") },
            leadingIcon = {
                if (searchIsActive) {
                    IconButton(onClick = { onSearchActiveChange(false) }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                } else {
                    Icon(Icons.Default.Search, contentDescription = "Search")
                }
            },
            trailingIcon = {
                if (searchQuery.isNotEmpty()) {
                    IconButton(onClick = { onSearchQueryChange("") }) { // FIX: Clear text
                        Icon(Icons.Default.Clear, contentDescription = "Clear search")
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(suggestions) { suggestion ->
                    ListItem(headlineContent = { Text(suggestion) }, leadingContent = {
                        Icon(
                            Icons.Default.LocationOn, contentDescription = null
                        )
                    }, modifier = Modifier
                        .clickable { onCitySelected(suggestion) }
                        .background(
                            MaterialTheme.colorScheme.background
                        )
                        .fillMaxSize())
                }
            }
        }
        // --- Spacer to separate Search Bar from Weather Display ---
        Spacer(modifier = Modifier.height(30.dp))



        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            when (val result = weatherResult.value) {
                is NetworkResponseClass.Error -> {
                    Text(text = result.message)
                }

                NetworkResponseClass.loading -> {
                    CircularProgressIndicator()
                }

                is NetworkResponseClass.Success -> {
                    Text(text = result.data.toString())
                }

                null -> {}

            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LargeSizedScreens(
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    searchIsActive: Boolean,
    onSearchActiveChange: (Boolean) -> Unit,
    onCitySelected: (String) -> Unit,
    suggestions: List<String>,
    viewModel: WeatherViewModel
) {

    val WeatherResult = viewModel.weatherResult.observeAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp), // More padding
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        SearchBar( // Still a full-screen overlay when active
            query = searchQuery,
            onQueryChange = onSearchQueryChange,
            onSearch = { submittedQuery ->
                onSearchActiveChange(false)
                onCitySelected(submittedQuery)
            },
            active = searchIsActive,
            onActiveChange = onSearchActiveChange,
            placeholder = { Text("Search City ....") },
            leadingIcon = {
                if (searchIsActive) {
                    IconButton(onClick = { onSearchActiveChange(false) }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                } else {
                    Icon(Icons.Default.Search, contentDescription = "Search")
                }
            },
            trailingIcon = {
                if (searchQuery.isNotEmpty()) {
                    IconButton(onClick = { onSearchQueryChange("") }) { // FIX: Clear text
                        Icon(Icons.Default.Clear, contentDescription = "Clear search")
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(suggestions) { suggestion ->
                    ListItem(headlineContent = { Text(suggestion) }, leadingContent = {
                        Icon(
                            Icons.Default.LocationOn, contentDescription = null
                        )
                    }, modifier = Modifier
                        .clickable { onCitySelected(suggestion) }
                        .background(
                            MaterialTheme.colorScheme.background
                        )
                        .fillMaxSize())
                }
            }
        }

        // --- Spacer to separate Search Bar from Weather Display ---
        Spacer(modifier = Modifier.height(30.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            when (val result = WeatherResult.value) {
                is NetworkResponseClass.Error -> {
                    Text(text = result.message)
                }

                NetworkResponseClass.loading -> {
                    CircularProgressIndicator()
                }

                is NetworkResponseClass.Success -> {
                    Text(text = result.data.toString())
                }

                null -> {}

            }
        }


    }
}


@Preview
@Composable
fun HomeScreenPreview() {
    val myViewModel: WeatherViewModel = viewModel()
    HomeScreen(myViewModel)
}

